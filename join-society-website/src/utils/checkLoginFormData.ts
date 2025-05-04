import toast from 'react-hot-toast';

export const checkLoginFormData = (data: { [k: string]: FormDataEntryValue }) => {
  if (data?.email === '' || data?.password === '') {
    toast.error('Email and password are required.');
    return false;
  }
  return true;
};
