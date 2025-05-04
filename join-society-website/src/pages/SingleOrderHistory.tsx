import { HttpStatusCode } from 'axios';
import { useEffect, useState } from 'react';
import toast from 'react-hot-toast';
import { useNavigate, useParams } from 'react-router-dom';
import { get } from '../axios/api';
import { POST_ORDERS } from '../constants/endpoints';
import { useAppSelector } from '../hooks';
import { formatDate } from '../utils/formatDate';

const SingleOrderHistory = () => {
  const navigate = useNavigate();
  const { loginStatus, loggedInUser } = useAppSelector((state) => state.auth);
  const { orderId } = useParams<{ orderId: string }>();
  const [order, setOrder] = useState<OrderConfirmation>();

  useEffect(() => {
    if (!loginStatus) {
      toast.error('Please login to view this page');
      navigate('/login');
    }
  }, [loginStatus]);

  useEffect(() => {
    const getOrder = async () => {
      const response = await get({ endpoint: `${POST_ORDERS}/${orderId}` });
      if (response?.status === HttpStatusCode.Ok) {
        setOrder(response.data);
      }
    };

    getOrder();
  }, [orderId]);

  useEffect(() => {
    if (loggedInUser && order && loggedInUser.id !== order.customer.id) {
      toast.error("Invalid Order Id");
      navigate("/");
    }
  }, [loggedInUser, order]);


  return (
    <div className="max-w-screen-2xl mx-auto pt-20 px-5">
      <h1 className="text-3xl font-bold mb-8">Order Details</h1>
      <div className="bg-white border border-gray-200 p-5 overflow-x-auto">
        <h2 className="text-2xl font-semibold mb-4">Order ID: {order?.id}</h2>
        <p className="mb-2">Date: {formatDate(order?.createdAt as string)}</p>
        <p className="mb-2">Subtotal: ${order?.orderSubtotal?.toFixed(2)}</p>
        <p className="mb-2">Shipping: ${order?.shippingFee?.toFixed(2)}</p>
        <p className="mb-2">Tax: ${order?.taxCharges?.toFixed(2)}</p>
        <p className="mb-2">Total: ${(order?.totalAmount.toFixed(2))}</p>
        <p className="mb-2">Status: {order?.orderStatus}</p>
        <h3 className="text-xl font-semibold mt-6 mb-4">Items</h3>
        <table className="singleOrder-table min-w-full bg-white border border-gray-200">
          <thead>
            <tr>
              <th className="py-3 px-4 border-b">Product Name</th>
              <th className="py-3 px-4 border-b">Color</th>
              <th className="py-3 px-4 border-b">Size</th>
              <th className="py-3 px-4 border-b">Quantity</th>
              <th className="py-3 px-4 border-b">Price</th>
            </tr>
          </thead>
          <tbody>
            {order && order.products.map((product) => (
              <tr key={product.productDetailId}>
                <td className="py-3 px-4 border-b">{product?.productSummary}</td>
                <td className="py-3 px-4 border-b">{product?.size}</td>
                <td className="py-3 px-4 border-b">{product?.color}</td>
                <td className="py-3 px-4 border-b text-center">{product?.quantity}</td>
                <td className="py-3 px-4 border-b text-right">${product?.price?.toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default SingleOrderHistory;
