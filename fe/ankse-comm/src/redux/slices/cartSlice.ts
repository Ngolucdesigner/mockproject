import { createSlice } from "@reduxjs/toolkit";
import { ProductProps } from "../../model/productProps";


type cartItem = ProductProps & {
  quantity: number;
  totalPrice: number;
  totalSale: number;
 
};

type CartState = {
  cartItems: cartItem[];
  totalAmount: number;
  totalQuantity: number;
  totalSalesPrice: number;
  totalFinal:number
};

const initialState: CartState = {
  cartItems: [],
  totalAmount: 0,
  totalQuantity: 0,
  totalSalesPrice:0,
  totalFinal:0
};

const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    addItem: (state, action) => {
      const newItem = action.payload;
      const existingItem = state.cartItems.find(
        (item) => item.id.toString() === newItem.id.toString()
      );

      state.totalQuantity++;
     

      if (!existingItem) {
        state.cartItems.push({
          id: newItem.id,
          productName: newItem.productName,
          category: newItem.category,
          imgUrl: newItem.imgUrl,
          price: newItem.price,
          priceSales: newItem.priceSales,
          quantity: 1,
          totalPrice: newItem.price,
          totalSale: newItem.priceSales
        });
      } else {
        existingItem.quantity++;
        existingItem.totalPrice =
          Number(existingItem.totalPrice) + Number(newItem.price);
          existingItem.totalSale = Number(existingItem.priceSales) + Number(newItem.priceSales);
      }
      // state.totalAmount = Number(state.cartItems.reduce((total, item) => total));

      state.totalAmount = Number(state.cartItems.reduce((total, item)=> Number(total) + Number(item.price)*Number(item.quantity), 0))
       
      state.totalSalesPrice = Number(state.cartItems.reduce((total, item)=>Number(total)+ Number(item.priceSales)*Number(item.quantity),0))
  
      state.totalFinal = state.totalAmount - (state.totalAmount*(state.totalSalesPrice/100))
     
      console.log(state.totalSalesPrice);
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
     
      state.totalSalesPrice = Number(state.cartItems.reduce((total, item)=>Number(total)+ Number(item.priceSales)*Number(item.quantity),0))

      
      state.totalFinal = state.totalAmount - (state.totalAmount*(state.totalSalesPrice/100))
      // state.totalAmount = Number(state.cartItems.reduce((total1, item)=> Number(total1.price) + Number(item.price)*Number(item.quantity)))
      // state.totalAmount = Number(
      //   state.cartItems.reduce((total, item) => total)
      // );
  },

  updateQuantity: (state, action) => {
    const itemIndex = state.cartItems.findIndex(item => item.id === action.payload.id);
    if (itemIndex >= 0) {
      const newItemQuantity = action.payload.quantity;
      const difference = newItemQuantity - state.cartItems[itemIndex].quantity;
      
      state.cartItems[itemIndex].quantity = newItemQuantity;

      // Cập nhật tổng số lượng và tổng tiền
      state.totalQuantity += difference;
      state.totalAmount += difference * state.cartItems[itemIndex].price;

      // Tổng tiền sau khi đã giảm giá
      state.totalFinal = state.totalAmount - (state.totalAmount * state.totalSalesPrice / 100);
    }
  },
}
});

export const cartActions = cartSlice.actions;
export const { updateQuantity } = cartSlice.actions;
export default cartSlice.reducer;
