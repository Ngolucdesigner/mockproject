import { createSlice } from '@reduxjs/toolkit'



type propsPage ={
    page:number;
}

const initialState:propsPage = {
    page: 1,
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