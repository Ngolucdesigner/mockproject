import { createSlice } from '@reduxjs/toolkit'
import { number } from 'yup';

const initialState = {
    page:number
}

const page = createSlice({
  name: "changePage",
  initialState,
  reducers: {
    addPage:(state, action)=>{
        state.page = action.payload;
    }
  }
});

export const  changePage = page.actions

export default page.reducer