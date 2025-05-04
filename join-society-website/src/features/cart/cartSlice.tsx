import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { calculateShippingFee, calculateTaxFee, isProductInStock } from '../../utils/productUtils';

type CartState = {
  productsInCart: ProductInCart[];
  subtotal: number;
  shippingFee: number;
  taxFee: number;
  totalAmount: number;
};

let initialState: CartState = {
  productsInCart: [],
  subtotal: 0,
  shippingFee: 0,
  taxFee: 0,
  totalAmount: 0,
};

const cartState = localStorage.getItem('cartState');
if (cartState) {
  initialState = JSON.parse(cartState);
}

export const cartSlice = createSlice({
  name: 'cart',
  initialState,
  reducers: {
    addProductToTheCart: (state, action: PayloadAction<ProductInCart>) => {
      const product = state.productsInCart.find(
        (product) => product.productDetailId === action.payload.productDetailId
      );

      if (product) {
        state.productsInCart = state.productsInCart.map((product) => {
          if (product.productDetailId === action.payload.productDetailId) {
            const newQuantity = product.quantity + action.payload.quantity;
            return {
              ...product,
              quantity: newQuantity,
              inStock: isProductInStock(newQuantity, product.inventoryCount),
            };
          }
          return product;
        });
      } else {
        state.productsInCart.push(action.payload);
      }
      cartSlice.caseReducers.calculateTotalPrice(state);

      localStorage.setItem('cartState', JSON.stringify(state));
    },
    removeProductFromTheCart: (state, action: PayloadAction<{ productDetailId: number }>) => {
      state.productsInCart = state.productsInCart.filter(
        (product) => product.productDetailId !== action.payload.productDetailId
      );
      cartSlice.caseReducers.calculateTotalPrice(state);
      localStorage.setItem('cartState', JSON.stringify(state));
    },
    updateProductQuantity: (state, action: PayloadAction<{ productDetailId: number; quantity: number }>) => {
      state.productsInCart = state.productsInCart.map((product) => {
        if (product.productDetailId === action.payload.productDetailId) {
          return {
            ...product,
            quantity: action.payload.quantity,
            inStock: isProductInStock(action.payload.quantity, product.inventoryCount),
          };
        }
        return product;
      });
      cartSlice.caseReducers.calculateTotalPrice(state);
      localStorage.setItem('cartState', JSON.stringify(state));
    },
    calculateTotalPrice: (state) => {
      const subtotal = state.productsInCart.reduce(
        (totalPrice, product) => totalPrice + product.pricePerQuantity * product.quantity,
        0
      );
      const shippingFee = calculateShippingFee(subtotal);
      const taxFee = calculateTaxFee(subtotal);

      state.subtotal = Number(subtotal.toFixed(2));
      state.shippingFee = Number(shippingFee.toFixed(2));
      state.taxFee = Number(taxFee.toFixed(2));
      state.totalAmount = Number((subtotal + shippingFee + taxFee).toFixed(2));
    },
    resetCart: (state) => {
      state.productsInCart = [];
      state.subtotal = 0;
      state.shippingFee = 0;
      state.taxFee = 0;
      state.totalAmount = 0;
      localStorage.removeItem('cartItem');
    }
  },
});

export const { addProductToTheCart, removeProductFromTheCart, updateProductQuantity, calculateTotalPrice, resetCart } =
  cartSlice.actions;

export default cartSlice.reducer;
