import toast from 'react-hot-toast';

export const checkRegisterFormData = (data: { [k: string]: FormDataEntryValue }): boolean => {
  if (data?.firstName === '') {
    toast.error('First Name is required.');
    return false;
  } else if (data?.lastName === '') {
    toast.error('Last Name is required.');
    return false;
  } else if (data?.email === '') {
    toast.error('Email Address is required.');
    return false;
  } else if (data?.phone === '') {
    toast.error('Phone Number is required.');
    return false;
  } else if (data?.password === '') {
    toast.error('Password is required.');
    return false;
  } else if (data?.password !== data?.confirmPassword) {
    toast.error('Passwords do not match.');
    return false;
  }

  return true;
};
