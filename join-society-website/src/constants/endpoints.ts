// Profile endpoints
export const MY_PROFILE = "/my-profile";
export const CREATE_MY_PROFILE = `${MY_PROFILE}/create`;
export const GET_USER_PROFILE_BY_ID = `${MY_PROFILE}/get/:id`;

// Login endpoints
export const LOGIN = "/login";

// Gender endpoints
export const GET_GENDERS = "/genders";

// Product endpoints
export const GET_PRODUCTS = "/products"
export const GET_PRODUCT_BY_ID = "/products/:id"

// Countries endpoints
export const GET_COUNTRIES = "/countries";

// Orders endpoint
export const POST_ORDERS = "/orders";
export const GET_ORDER_HISTORY = "/orders/history";
export const GET_ORDER_BY_ID = "/orders/:id";