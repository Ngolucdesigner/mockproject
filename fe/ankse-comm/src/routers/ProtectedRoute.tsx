import React, { ReactNode } from 'react';
import { Navigate, Outlet } from 'react-router-dom';

const ProtectedRoute = () => {
    const currentUser=false;

    return currentUser? <Outlet/> : <Navigate to={"/dashboard/login"}/>;
};

export default ProtectedRoute;