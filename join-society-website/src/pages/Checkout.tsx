import { HttpStatusCode } from 'axios';
import { useEffect, useState } from 'react';
import toast from 'react-hot-toast';
import { HiTrash as TrashIcon } from 'react-icons/hi2';
import { useNavigate } from 'react-router-dom';
import { get, post } from '../axios/api';
import { Button, StandardSelectInput, WithSelectInputWrapper } from '../components';
import { GET_COUNTRIES, POST_ORDERS } from '../constants/endpoints';
import { removeProductFromTheCart, resetCart } from '../features/cart/cartSlice';
import { useAppDispatch, useAppSelector } from '../hooks';
import { checkCheckoutFormData } from '../utils/checkCheckoutFormData';

const Checkout = () => {
  const { productsInCart, subtotal, shippingFee, taxFee, totalAmount } = useAppSelector((state) => state.cart);
  const { loginStatus, loggedInUser } = useAppSelector((state) => state.auth);

  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const SelectInputUpgrade = WithSelectInputWrapper(StandardSelectInput);

  const [countries, setCountries] = useState<CountryDTO[]>([]);
  const [countryId, setCountryId] = useState<number>(0);
  const [states, setStates] = useState<StateDTO[]>([]);
  const [stateId, setStateId] = useState<number>(0);

  useEffect(() => {
    const getCountries = async () => {
      const response = await get({ endpoint: GET_COUNTRIES });
      if (response?.status === HttpStatusCode.Ok) {
        const fetchedCountries = (response.data as CountryDTO[]) || [];
        setCountries(fetchedCountries);
        if (fetchedCountries.length > 0) {
          setCountryId(fetchedCountries[0].id);
          setStates(fetchedCountries[0].states);
          if (fetchedCountries[0].states.length > 0) {
            setStateId(fetchedCountries[0].states[0].id || 0);
          }
        }
      }
    };

    getCountries();
  }, []);

  const handleCheckoutSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);
    const data = Object.fromEntries(formData);

    const checkoutData = {
      data,
      products: productsInCart,
      subtotal: subtotal,
    };

    if (!checkCheckoutFormData(checkoutData)) return;

    const customer: CustomerDTO = {};
    if (loginStatus && loggedInUser && data.emailAddress === loggedInUser.email) {
      customer.id = loggedInUser.id;
    } else {
      customer.email = data.emailAddress.toString();
      customer.firstName = data.firstName.toString();
      customer.lastName = data.lastName.toString();
    }

    const products: ProductInOrder[] = productsInCart.map(
      (product): ProductInOrder => ({
        productDetailId: product.productDetailId,
        price: product.pricePerQuantity,
        quantity: product.quantity,
      })
    );

    const orderRequest: OrderRequestDTO = {
      customer: customer,
      shippingAddress: {
        line1: data.addressLine1.toString(),
        line2: data.addressLine2?.toString() || '',
        city: data.city.toString(),
        state: states.find((state) => state.id === Number(data.state)),
        postalCode: data.postalCode.toString(),
      },
      billingAddress: {
        line1: data.addressLine1.toString(),
        line2: data.addressLine2?.toString() || '',
        city: data.city.toString(),
        state: states.find((state) => state.id === Number(data.state)),
        postalCode: data.postalCode.toString(),
      },
      paymentMethod: {
        paymentType: 'CREDIT_CARD',
        nameOnCard: data.nameOnCard.toString(),
        cardNumber: data.cardNumber.toString(),
        cardExpiry: data.expirationDate.toString(),
      },
      products: products,
      orderSubtotal: subtotal,
      shippingFee: shippingFee,
      taxCharges: taxFee,
      totalAmount: totalAmount,
    };

    const response = await post({ endpoint: POST_ORDERS, data: orderRequest });
    if (response?.status === HttpStatusCode.Ok) {
      toast.success('Order Placed Successfully!');
      dispatch(resetCart());
      navigate(`/order-history/${response.data.id}`);
    }
  };

  return (
    <div className="mx-auto max-w-screen-2xl">
      <div className="pb-24 pt-16 px-5 max-[400px]:px-3">
        <h2 className="sr-only">Checkout</h2>

        <form onSubmit={handleCheckoutSubmit} className="lg:grid lg:grid-cols-2 lg:gap-x-12 xl:gap-x-16">
          <div>
            <div>
              <h2 className="text-lg font-medium text-gray-900">Contact information</h2>

              {/* Email */}
              <div className="mt-4">
                <label htmlFor="emailAddress" className="block text-sm font-medium text-gray-700">
                  Email address
                </label>
                <div className="mt-1">
                  <input
                    type="email"
                    id="emailAddress"
                    name="emailAddress"
                    autoComplete="email"
                    defaultValue={loggedInUser?.email}
                    className="block w-full py-2 indent-2 border-gray-300 outline-none focus:border-gray-400 border border shadow-sm sm:text-sm"
                    required={true}
                  />
                </div>
              </div>
            </div>

            <div className="mt-10 border-t border-gray-200 pt-10">
              <h2 className="text-lg font-medium text-gray-900">Shipping information</h2>

              <div className="mt-4 grid grid-cols-1 gap-y-6 sm:grid-cols-2 sm:gap-x-4">
                {/* First Name */}
                <div>
                  <label htmlFor="firstName" className="block text-sm font-medium text-gray-700">
                    First name
                  </label>
                  <div className="mt-1">
                    <input
                      type="text"
                      id="firstName"
                      name="firstName"
                      defaultValue={loggedInUser?.firstName}
                      className="block w-full py-2 indent-2 border-gray-300 outline-none focus:border-gray-400 border border shadow-sm sm:text-sm"
                      required={true}
                    />
                  </div>
                </div>

                {/* Last Name */}
                <div>
                  <label htmlFor="lastName" className="block text-sm font-medium text-gray-700">
                    Last name
                  </label>
                  <div className="mt-1">
                    <input
                      type="text"
                      id="lastName"
                      name="lastName"
                      defaultValue={loggedInUser?.lastName}
                      className="block w-full py-2 indent-2 border-gray-300 outline-none focus:border-gray-400 border border shadow-sm sm:text-sm"
                      required={true}
                    />
                  </div>
                </div>

                {/* Address Line 1 */}
                <div className="sm:col-span-2">
                  <label htmlFor="addressLine1" className="block text-sm font-medium text-gray-700">
                    Address Line 1
                  </label>
                  <div className="mt-1">
                    <input
                      type="text"
                      name="addressLine1"
                      id="addressLine1"
                      className="block w-full py-2 indent-2 border-gray-300 outline-none focus:border-gray-400 border border shadow-sm sm:text-sm"
                      required={true}
                    />
                  </div>
                </div>

                {/* Address Line 2 */}
                <div className="sm:col-span-2">
                  <label htmlFor="addressLine2" className="block text-sm font-medium text-gray-700">
                    Address Line 2
                  </label>
                  <div className="mt-1">
                    <input
                      type="text"
                      name="addressLine2"
                      id="addressLine1"
                      className="block w-full py-2 indent-2 border-gray-300 outline-none focus:border-gray-400 border border shadow-sm sm:text-sm"
                      required={false}
                    />
                  </div>
                </div>

                {/* City */}
                <div>
                  <label htmlFor="city" className="block text-sm font-medium text-gray-700">
                    City
                  </label>
                  <div className="mt-1">
                    <input
                      type="text"
                      name="city"
                      id="city"
                      className="block w-full py-2 indent-2 border-gray-300 outline-none focus:border-gray-400 border border shadow-sm sm:text-sm"
                      required={true}
                    />
                  </div>
                </div>

                {/* Country */}
                <div>
                  <label htmlFor="country" className="block text-sm font-medium text-gray-700">
                    Country
                  </label>
                  <div className="mt-1">
                    <SelectInputUpgrade
                      id="country"
                      name="country"
                      selectList={countries.map((country) => ({ id: country.id, value: country.name }))}
                      value={countryId.toString()}
                      onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setCountryId(() => Number(e.target.value))}
                    />
                  </div>
                </div>

                {/* State */}
                <div>
                  <label htmlFor="state" className="block text-sm font-medium text-gray-700">
                    State
                  </label>
                  <div className="mt-1">
                    <SelectInputUpgrade
                      id="state"
                      name="state"
                      selectList={states.map((state) => ({ id: state.id, value: state.name }))}
                      value={stateId.toString()}
                      onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setStateId(() => Number(e.target.value))}
                    />
                  </div>
                </div>

                {/* Postal Code */}
                <div>
                  <label htmlFor="postalCode" className="block text-sm font-medium text-gray-700">
                    Postal code
                  </label>
                  <div className="mt-1">
                    <input
                      type="text"
                      name="postalCode"
                      id="postalCode"
                      className="block w-full py-2 indent-2 border-gray-300 outline-none focus:border-gray-400 border border shadow-sm sm:text-sm"
                      required={true}
                    />
                  </div>
                </div>
              </div>
            </div>

            {/* Payment */}
            <div className="mt-10 border-t border-gray-200 pt-10">
              <h2 className="text-lg font-medium text-gray-900">Payment</h2>

              <div className="mt-6 grid grid-cols-4 gap-x-4 gap-y-6">
                {/* Card Number */}
                <div className="col-span-4">
                  <label htmlFor="cardNumber" className="block text-sm font-medium text-gray-700">
                    Card number
                  </label>
                  <div className="mt-1">
                    <input
                      type="text"
                      id="cardNumber"
                      name="cardNumber"
                      className="block w-full py-2 indent-2 border-gray-300 outline-none focus:border-gray-400 border border shadow-sm sm:text-sm"
                      required={true}
                      maxLength={16}
                    />
                  </div>
                </div>

                {/* Name on Card */}
                <div className="col-span-4">
                  <label htmlFor="nameOnCard" className="block text-sm font-medium text-gray-700">
                    Name on card
                  </label>
                  <div className="mt-1">
                    <input
                      type="text"
                      id="nameOnCard"
                      name="nameOnCard"
                      className="block w-full py-2 indent-2 border-gray-300 outline-none focus:border-gray-400 border border shadow-sm sm:text-sm"
                      required={true}
                    />
                  </div>
                </div>

                {/* Card Expiry */}
                <div className="col-span-3">
                  <label htmlFor="expirationDate" className="block text-sm font-medium text-gray-700">
                    Expiration date (MM/YY)
                  </label>
                  <div className="mt-1">
                    <input
                      type="text"
                      name="expirationDate"
                      id="expirationDate"
                      className="block w-full py-2 indent-2 border-gray-300 outline-none focus:border-gray-400 border border shadow-sm sm:text-sm"
                      required={true}
                      maxLength={5}
                    />
                  </div>
                </div>

                {/* CVV */}
                <div>
                  <label htmlFor="cvv" className="block text-sm font-medium text-gray-700">
                    CVV
                  </label>
                  <div className="mt-1">
                    <input
                      type="password"
                      name="cvv"
                      id="cvv"
                      className="block w-full py-2 indent-2 border-gray-300 outline-none focus:border-gray-400 border border shadow-sm sm:text-sm"
                      required={true}
                      maxLength={4}
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>

          {/* Order summary */}
          <div className="mt-10 lg:mt-0">
            <h2 className="text-lg font-medium text-gray-900">Order summary</h2>

            <div className="mt-4 border border-gray-200 bg-white shadow-sm">
              <h3 className="sr-only">Items in your cart</h3>
              <ul role="list" className="divide-y divide-gray-200">
                {productsInCart.map((product) => (
                  <li key={product?.productDetailId} className="flex px-4 py-6 sm:px-6">
                    <div className="flex-shrink-0">
                      <img src={product?.image} alt={product?.productSummary} className="w-20 rounded-md" />
                    </div>

                    <div className="ml-6 flex flex-1 flex-col">
                      <div className="flex">
                        <div className="min-w-0 flex-1">
                          <h4 className="text-sm font-medium text-gray-700 hover:text-gray-800">
                            {product?.productSummary}
                          </h4>
                          <p className="mt-1 text-sm text-gray-500">{product?.color}</p>
                          <p className="mt-1 text-sm text-gray-500">{product?.size}</p>
                        </div>

                        <div className="ml-4 flow-root flex-shrink-0">
                          <button
                            type="button"
                            className="-m-2.5 flex items-center justify-center bg-white p-2.5 text-gray-400 hover:text-gray-500"
                            onClick={() =>
                              dispatch(removeProductFromTheCart({ productDetailId: product?.productDetailId }))
                            }
                          >
                            <span className="sr-only">Remove</span>
                            <TrashIcon className="h-5 w-5" aria-hidden="true" />
                          </button>
                        </div>
                      </div>

                      <div className="flex flex-1 items-end justify-between pt-2">
                        <p className="mt-1 text-sm font-medium text-gray-900">${product?.pricePerQuantity}</p>

                        <div className="ml-4">
                          <p className="text-base">Quantity: {product?.quantity}</p>
                        </div>
                      </div>
                    </div>
                  </li>
                ))}
              </ul>
              <dl className="space-y-6 border-t border-gray-200 px-4 py-6 sm:px-6">
                <div className="flex items-center justify-between">
                  <dt className="text-sm">Subtotal</dt>
                  <dd className="text-sm font-medium text-gray-900">${subtotal}</dd>
                </div>
                <div className="flex items-center justify-between">
                  <dt className="text-sm">Shipping</dt>
                  <dd className="text-sm font-medium text-gray-900">${shippingFee.toFixed(2)}</dd>
                </div>
                <div className="flex items-center justify-between">
                  <dt className="text-sm">Taxes</dt>
                  <dd className="text-sm font-medium text-gray-900">${taxFee.toFixed(2)}</dd>
                </div>
                <div className="flex items-center justify-between border-t border-gray-200 pt-6">
                  <dt className="text-base font-medium">Total</dt>
                  <dd className="text-base font-medium text-gray-900">${totalAmount.toFixed(2)}</dd>
                </div>
              </dl>

              <div className="border-t border-gray-200 px-4 py-6 sm:px-6">
                <Button text="Confirm Order" mode="brown" />
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Checkout;
