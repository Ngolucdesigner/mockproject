import React from 'react';
import "./Notfound.scss"
import { Link } from 'react-router-dom';
const NotFound = () => {
    return (
        <div className='notfound'>
      <h1>Not Found</h1>
            <p> URL not match in system</p>
            <p><Link to={"/home"}  className ="link-home"> Back to home</Link></p>
        </div>
    );
};

export default NotFound;