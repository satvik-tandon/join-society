import toast from "react-hot-toast";

const REQUIRED_FIELDS = {
  "emailAddress": "Email Address",
  "firstName": "First Name",
  "lastName": "Last Name",
  "addressLine1": "Address Line 1",
  "city": "City",
  "country": "Country",
  "state": "State",
  "postalCode": "Postal Code",
  "cardNumber": "Card Number",
  "nameOnCard": "Name on Card",
  "expirationDate": "Card Expiry",
  "cvv": "CVV",
};

export const checkCheckoutFormData = (checkoutData: {
  data: {
    [k: string]: FormDataEntryValue;
  };
  products: ProductInCart[];
  subtotal: number;
}) => {
  if (!checkoutData.data) {
    toast.error("Please fill out the information.")
    return false;
  }

  for (const [field, fieldLabel] of Object.entries(REQUIRED_FIELDS)) {
    if (!(field in checkoutData.data) || checkoutData.data[field] === "") {
      toast.error(`${fieldLabel} is required.`);
      return false;
    }
  }

  return true;
};
