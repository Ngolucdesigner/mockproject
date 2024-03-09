import { configureStore } from "@reduxjs/toolkit";
import cartSlice from "./slices/cartSlice";
import cartHeart from "./slices/cartHeart";
import loadProduct from "./slices/loadProduct";
import order from "./slices/order";
import page from "./slices/page";
import loadAccount from "./slices/loadAccount";
import isLogin from "./slices/isLogin";


const store = configureStore({
    reducer:{
        cart: cartSlice,
        heart: cartHeart,
        reload: loadProduct,
        quantity: order,
        changePage:page,
        reloadAccount: loadAccount ,
        login: isLogin
    }
})

export default store;