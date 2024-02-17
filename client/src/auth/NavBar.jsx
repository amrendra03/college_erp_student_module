// NavBar.jsx
import React from 'react';
import { Link } from 'react-router-dom';

const NavBar = ({ authenticated, onLogout }) => {
   const navStyles = {
      display: 'flex',
      justifyContent: 'space-around',
      padding: '10px',
      backgroundColor: '#333',
      color: '#fff',
   };

   const linkStyles = {
      textDecoration: 'none',
      color: '#fff',
   };

   return (
      <nav style={navStyles}>
         <Link to="/" style={linkStyles}>
            Welcome
         </Link>
         <Link to="/home" style={linkStyles}>
            Home
         </Link>
         {authenticated ? (
            <button onClick={onLogout}>Logout</button>
         ) : (
            <Link to="/login" style={linkStyles}>
               Login
            </Link>
         )}
      </nav>
   );
};

export default NavBar;
