import { createSlice } from "@reduxjs/toolkit";
import { loginInfo } from "../../model/login";

type loginInformation = loginInfo & {
  fullName: string;
};

type stateLogin = {
  loginState: loginInformation;
  isLogin: Boolean;
};
const initialState: stateLogin = {
  loginState: {
    fullName: "",
    token: "",
    username: "",
    email: "",
    id: "",
    roles: [],
    avatar: { fileName: "", fileType: "", id: "", url: "" },
  },
  isLogin: false,
};
const retrieveCartStateFromSessionStorage = (): stateLogin => {
  const storedCartState = sessionStorage.getItem("loginState");
  return storedCartState ? JSON.parse(storedCartState) : null;
};

const isLogin = createSlice({
  name: "login",
  initialState: retrieveCartStateFromSessionStorage()
    ? retrieveCartStateFromSessionStorage() || initialState
    : initialState,
  reducers: {
    setLogin: (state, action) => {
      state.loginState = action.payload;
    },

    isLogin: (state, action) => {
      state.isLogin = action.payload;
      sessionStorage.setItem("loginState", JSON.stringify(state));
    },
    clearInfo: (state) => {
      state.isLogin = false;
      state.loginState = {
        email: "",
        fullName: "",
        id: "",
        roles: [],
        token: "",
        username: "",
        avatar: { fileName: "", fileType: "", id: "", url: "" },
      };
      // Clear the sessionStorage
      sessionStorage.removeItem("loginState");
    },
  },
});

export const login = isLogin.actions;

export default isLogin.reducer;
