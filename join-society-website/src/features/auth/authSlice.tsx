import { createSlice, PayloadAction } from '@reduxjs/toolkit';

type AuthState = {
  loggedInUser?: CustomerDTO;
  loginStatus: boolean;
};

const initialState: AuthState = {
  loggedInUser: JSON.parse(localStorage.getItem('user') || '{}'),
  loginStatus: JSON.parse(localStorage.getItem('user') || '{}').id ? true : false,
};

export const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setLoginStatus: (state, action: PayloadAction<AuthState>) => {
      state.loginStatus = action.payload.loginStatus;
      state.loggedInUser = action.payload.loggedInUser;
    },
  },
});

export const { setLoginStatus } = authSlice.actions;

export default authSlice.reducer;
