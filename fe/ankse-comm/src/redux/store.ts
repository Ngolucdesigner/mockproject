import { configureStore } from "@reduxjs/toolkit";
import cartSlice from "./slices/cartSlice";
import cartHeart from "./slices/cartHeart";
import loadProduct from "./slices/loadProduct";
import order from "./slices/order";
import page from "./slices/page";


const store = configureStore({
    reducer:{
        cart: cartSlice,
        heart: cartHeart,
        reload: loadProduct,
        quantity: order,
        changePage:page
    }
})

export default store;