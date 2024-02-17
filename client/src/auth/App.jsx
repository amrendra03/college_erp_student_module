// App.jsx
import React, { useEffect, useState } from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import Home from './Home';
import Login from './Login';
import NavBar from './NavBar';
import Welcome from './Welcome';


const containerStyles = {
   display: 'flex',
   flexDirection: 'column',
   minHeight: '100vh',
};

const App = () => {
   const [authenticated, setAuthenticated] = useState(false);

   useEffect(() => {
      // Check for the presence of a token (modify this logic based on your authentication mechanism)
      const token = localStorage.getItem('token');
      const isAuthenticatedUser = !!token;

      setAuthenticated(isAuthenticatedUser);
   }, []);

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
               path="/login"
               element={authenticated ? <Navigate to="/welcome" /> : <Login onLogin={handleLogin} />}
            />
         </Routes>
      </div>
   );
};

export default App;
