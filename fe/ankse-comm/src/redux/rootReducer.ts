import { combineReducers } from 'redux'
import cartSlice from './slices/cartSlice'
import cartHeart from './slices/cartHeart'
import loadProduct from './slices/loadProduct'
import order from './slices/order'
export const rootReducer = combineReducers({
   cart: cartSlice,
   heart:cartHeart,
   reload: loadProduct,
   quantity: order
    // trong trường hợp có nhiều reducer thì bạn cho vào đây
  })
  export type TReducers = ReturnType<typeof rootReducer>