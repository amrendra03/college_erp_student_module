// App.jsx
import React, { useEffect, useState } from 'react';
import { Navigate, Route, Routes, useLocation } from 'react-router-dom';
import Home from './Home';
import Login from './Login';
import NavBar from './NavBar';
import Payment from './Payment';
import Profile from './Profile';
import Welcome from './Welcome';

const containerStyles = {
   display: 'flex',
   flexDirection: 'column',
   minHeight: '100vh',
};

const App = () => {
   const [authenticated, setAuthenticated] = useState(false);
   const [redirectedFrom, setRedirectedFrom] = useState(null); // To store the redirected route
   const location = useLocation();

   useEffect(() => {
      // Check for the presence of a token (modify this logic based on your authentication mechanism)
      const token = localStorage.getItem('token');
      const isAuthenticatedUser = !!token;

      setAuthenticated(isAuthenticatedUser);

      // If not authenticated, store the current route in redirectedFrom state
      if (!isAuthenticatedUser) {
         setRedirectedFrom(location.pathname);
      }
   }, [location.pathname]);

   const handleLogin = () => {
      setAuthenticated(true);
   };

   const handleLogout = () => {
      // Clear the token from local storage on logout
      localStorage.removeItem('token');
      setAuthenticated(false);
   };

   return (
      <div style={containerStyles}>
         <NavBar authenticated={authenticated} onLogout={handleLogout} />
         <Routes>
            <Route
               path="/"
               element={<Welcome />}
            />
            <Route
               path="/home"
               element={authenticated ? <Home /> : <Navigate to="/login" />}
            />
            <Route
               path="/profile"
               element={
                  authenticated ? (
                     <Profile />
                  ) : (
                     <Navigate to="/login" state={{ from: '/profile' }} />
                  )
               }
            />
            <Route
               path="/login"
               element={
                  authenticated ? (
                     <Navigate to={redirectedFrom || '/welcome'} />
                  ) : (
                     <Login onLogin={handleLogin} setRedirectedFrom={setRedirectedFrom} />
                  )
               }
            />
            <Route
               path="/payment"
               element={authenticated ? <Payment /> : <Navigate to="/login" />}
            />
         </Routes>
      </div>
   );
};

export default App;
