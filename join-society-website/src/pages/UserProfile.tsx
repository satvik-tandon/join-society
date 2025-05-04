import { HttpStatusCode } from 'axios';
import { useEffect, useState } from 'react';
import toast from 'react-hot-toast';
import { Link, useNavigate } from 'react-router-dom';
import { get } from '../axios/api';
import Button from '../components/common/Button';
import { GET_USER_PROFILE_BY_ID } from '../constants/endpoints';
import { setLoginStatus } from '../features/auth/authSlice';
import { store } from '../store';

const UserProfile = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState<User>();

  const logout = () => {
    toast.success('Logged out successfully');
    localStorage.removeItem('user');
    store.dispatch(setLoginStatus({ loginStatus: false }));
    navigate('/login');
  };

  const fetchUser = async (userId: number) => {
    const response = await get({ endpoint: GET_USER_PROFILE_BY_ID.replace(':id', userId.toString()) });
    if (response?.status === HttpStatusCode.Ok) {
      setUser(response.data);
    } else {
      localStorage.removeItem('user');
      store.dispatch(setLoginStatus({ loginStatus: false }));
      navigate('/login');
    }
  };

  const updateUser = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    // Get form data
    // const formData = new FormData(e.currentTarget);
    // const data = Object.fromEntries(formData);

    // TODO: Update user
    toast.error('Update User Coming Soon!');
  };

  useEffect(() => {
    const userId = JSON.parse(localStorage.getItem('user') || '{}').id;
    if (!userId) {
      navigate('/login');
    } else {
      fetchUser(userId);
    }
  }, [navigate]);

  return (
    <div className="max-w-screen-lg mx-auto mt-24 px-5">
      <h1 className="text-3xl font-bold mb-8">User Profile</h1>
      <form className="flex flex-col gap-6" onSubmit={updateUser}>
        <div className="flex flex-col gap-1">
          <label htmlFor="firstname">First Name</label>
          <input
            type="text"
            className="bg-white border border-black text-xl py-2 px-3 w-full outline-none max-[450px]:text-base"
            placeholder="Enter first name"
            id="firstname"
            name="name"
            defaultValue={user?.firstName}
          />
        </div>
        <div className="flex flex-col gap-1">
          <label htmlFor="lastname">Last Name</label>
          <input
            type="text"
            className="bg-white border border-black text-xl py-2 px-3 w-full outline-none max-[450px]:text-base"
            placeholder="Enter last name"
            id="lastname"
            name="lastname"
            defaultValue={user?.lastName}
          />
        </div>
        <div className="flex flex-col gap-1">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            className="bg-white border border-black text-xl py-2 px-3 w-full outline-none max-[450px]:text-base"
            placeholder="Enter email address"
            id="email"
            name="email"
            defaultValue={user?.email}
          />
        </div>
        <div className="flex flex-col gap-1">
          <label htmlFor="phone">Phone Number</label>
          <input
            type="test"
            className="bg-white border border-black text-xl py-2 px-3 w-full outline-none max-[450px]:text-base"
            placeholder="Enter phone number"
            id="phone"
            name="phone"
            defaultValue={user?.phone}
          />
        </div>
        <div className="flex flex-col gap-1">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            className="bg-white border border-black text-xl py-2 px-3 w-full outline-none max-[450px]:text-base"
            placeholder="Enter password"
            id="password"
            name="password"
            defaultValue={user?.password}
          />
        </div>
        <Button type="submit" text="Update Profile" mode="brown" />
        <Link
          to="/order-history"
          className="bg-white text-black text-center text-xl border border-gray-400 font-normal tracking-[0.6px] leading-[72px] w-full h-12 flex items-center justify-center max-md:text-base"
        >
          Order History
        </Link>
        <Button onClick={logout} text="Logout" mode="white" />
      </form>
    </div>
  );
};

export default UserProfile;
