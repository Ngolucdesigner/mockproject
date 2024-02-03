import { createSlice } from '@reduxjs/toolkit'

type loadProps ={
  load: boolean
}

const initialState: loadProps = {
    load : false
}

const loadProduct = createSlice({
  name: "reload",
  initialState,
  reducers: {
        reloadProduct: (state,action)=>{
            state.load = action.payload;
           
        }
        

  },
});

export const reloadProduct = loadProduct.actions

export default loadProduct.reducer