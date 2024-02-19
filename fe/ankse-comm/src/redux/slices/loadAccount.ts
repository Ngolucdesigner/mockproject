import { createSlice } from '@reduxjs/toolkit'
type loadProps ={
    load: boolean
  }
  
  const initialState: loadProps = {
    load : false
}


const loadAccount = createSlice({
  name: "reloadAccount",
  initialState,
  reducers: {
    reloadAccount: (state,action)=>{
        state.load = action.payload;
    }
  }
});

export const reloadAccount = loadAccount.actions

export default loadAccount.reducer