import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./features/auth/authSlice";
import genderReducer from "./features/gender/genderSlice";
import cartReducer from "./features/cart/cartSlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    gender: genderReducer,
    cart: cartReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
