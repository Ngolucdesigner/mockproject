import { combineReducers } from 'redux'
import cartSlice from './slices/cartSlice'
export const rootReducer = combineReducers({
   cart: cartSlice,
    // trong trường hợp có nhiều reducer thì bạn cho vào đây
  })
  export type TReducers = ReturnType<typeof rootReducer>