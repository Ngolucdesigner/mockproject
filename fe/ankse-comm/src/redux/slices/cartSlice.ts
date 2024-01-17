import { createSlice } from "@reduxjs/toolkit";
import { ProductProps } from "../../model/productProps";

type cartItem = ProductProps & {
  quantity: number;
  totalPrice: number;
};

type CartState = {
  cartItems: cartItem[];
  totalAmount: number;
  totalQuantity: number;
};

const initialState: CartState = {
  cartItems: [],
  totalAmount: 0,
  totalQuantity: 0,
};

const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    addItem: (state, action) => {
      const newItem = action.payload;
      const existingItem = state.cartItems.find(
        (item) => item.id === newItem.id
      );

      state.totalQuantity++;

      if (!existingItem) {
        state.cartItems.push({
          id: newItem.id,
          productName: newItem.productName,
          category: newItem.category,
          imgUrl: newItem.imgUrl,
          price: newItem.price,
          quantity: 1,
          totalPrice: newItem.price,
        });
      } else {
        existingItem.quantity++;
        existingItem.totalPrice =
          Number(existingItem.totalPrice) + Number(newItem.price);
      }
      // state.totalAmount = Number(state.cartItems.reduce((total, item) => total));

      state.totalAmount = Number(state.cartItems.reduce((total, item)=> Number(total) + Number(item.price)*Number(item.quantity), 0))
      console.log(state.totalQuantity);
      // console.log(state.cartItems);
      // console.log(newItem);
    },

    deleteItem: (state, action) => {
      const id = action.payload;
      const existingItem = state.cartItems.find(item => item.id === id);

      if(existingItem){
        state.cartItems = state.cartItems.filter(item => item.id!==id);
        state.totalQuantity = state.totalQuantity- existingItem.quantity;
      }
      state.totalAmount = Number(state.cartItems.reduce((total, item)=> Number(total) + Number(item.price)*Number(item.quantity), 0))
      // state.totalAmount = Number(state.cartItems.reduce((total1, item)=> Number(total1.price) + Number(item.price)*Number(item.quantity)))
      // state.totalAmount = Number(
      //   state.cartItems.reduce((total, item) => total)
      // );

  },
}
});

export const cartActions = cartSlice.actions;

export default cartSlice.reducer;
