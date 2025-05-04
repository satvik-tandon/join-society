import { HttpStatusCode } from 'axios';
import { useEffect, useState } from 'react';
import toast from 'react-hot-toast';
import { Link, useNavigate } from 'react-router-dom';
import { get } from '../axios/api';
import { GET_ORDER_HISTORY } from '../constants/endpoints';
import { useAppSelector } from '../hooks';
import { formatDate } from '../utils/formatDate';
import NotFound from '../components/common/NotFound';

const OrderHistory = () => {
  const { loginStatus, loggedInUser } = useAppSelector((state) => state.auth);
  const [orders, setOrders] = useState<OrderConfirmation[]>([]);

  const navigate = useNavigate();

  useEffect(() => {
    if (!loginStatus) {
      toast.error('Please login to view this page');
      navigate('/login');
    }
  }, [loginStatus, navigate]);

  useEffect(() => {
    const getOrderHistory = async () => {
      if (loginStatus && loggedInUser) {
        const response = await get({ endpoint: `${GET_ORDER_HISTORY}/${loggedInUser.id}` });
        if (response?.status === HttpStatusCode.Ok) {
          setOrders(response.data);
        }
      }
    };

    getOrderHistory();
  }, [loginStatus, loggedInUser]);

  return (
    <div className="max-w-screen-2xl mx-auto pt-20 px-5">
      <h1 className="text-3xl font-bold mb-8">Order History</h1>
      {orders.length > 0 ? (
        orders.map((order) => (
          <div className="overflow-x-auto">
            <table className="min-w-full bg-white border border-gray-200">
              <thead>
                <tr>
                  <th className="py-3 px-4 border-b">Order ID</th>
                  <th className="py-3 px-4 border-b">Date</th>
                  <th className="py-3 px-4 border-b">Total</th>
                  <th className="py-3 px-4 border-b">Status</th>
                  <th className="py-3 px-4 border-b">Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr key={order.id}>
                  <td className="py-3 px-4 border-b text-center">{order.id}</td>
                  <td className="py-3 px-4 border-b text-center">{formatDate(order.createdAt)}</td>
                  <td className="py-3 px-4 border-b text-center">${order.totalAmount}</td>
                  <td className="py-3 px-4 border-b text-center">{order.orderStatus}</td>
                  <td className="py-3 px-4 border-b text-center">
                    <Link to={`/order-history/${order.id}`} className="text-blue-500 hover:underline">
                      View Details
                    </Link>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        ))
      ) : (
        <NotFound title="No Orders Yet!" message="Start exploring our collection and find something you love!">
          <Link
            to="/"
            className="text-white bg-secondaryBrown text-center text-xl font-normal tracking-[0.6px] leading-[72px] w-[400px] mx-auto mt-5 h-12 flex items-center justify-center max-md:text-base"
          >
            Shop Now
          </Link>
        </NotFound>
      )}
    </div>
  );
};

export default OrderHistory;
