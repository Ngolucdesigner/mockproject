import { createSlice } from '@reduxjs/toolkit'

type orderQuantity = {
    quantityOder: number;
    quantitySales: number;
}

const initialState : orderQuantity= {
    quantityOder:0,
    quantitySales: 0
}

const order = createSlice({
  name: "quantity",
  initialState,
  reducers: {
    quantityOrder: (state, action)=>{
        state.quantityOder = action.payload;
    },
    quantitySales:(state, action)=>{
        state.quantitySales=  action.payload
    }
  }
});

export const quantity = order.actions

export default order.reducer