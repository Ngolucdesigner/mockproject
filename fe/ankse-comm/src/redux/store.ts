import { configureStore } from "@reduxjs/toolkit";
import cartSlice from "./slices/cartSlice";
import cartHeart from "./slices/cartHeart";
import loadProduct from "./slices/loadProduct";
import order from "./slices/order";


const store = configureStore({
    reducer:{
        cart: cartSlice,
        heart: cartHeart,
        reload: loadProduct,
        quantity: order
    }
})

export default store;