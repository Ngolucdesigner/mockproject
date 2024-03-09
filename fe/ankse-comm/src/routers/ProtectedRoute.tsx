import React  from 'react';
import { useSelector } from 'react-redux';
import { Navigate, Outlet } from 'react-router-dom';
import { TReducers } from '../redux/rootReducer';


const ProtectedRoute = () => {

     const currentUser = useSelector<TReducers>((state)=> state.login.isLogin) 
     const roles = useSelector<TReducers>((state)=> state.login.loginState.roles )
   
    return currentUser && roles == "ADMIN" ? <Outlet/> : <Navigate to={"/dashboard/login"}/>;
};

export default ProtectedRoute;