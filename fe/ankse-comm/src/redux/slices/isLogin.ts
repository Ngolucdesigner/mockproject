import { createSlice } from '@reduxjs/toolkit'



const initialState = {
    isLogin: false,
    username: null,
}

const isLogin = createSlice({
  name: "login",
  initialState,
  reducers: {
      isLogin:(state, action)=>{
        state.isLogin = action.payload
      }

  }
});

export const login = isLogin.actions

export default isLogin.reducer