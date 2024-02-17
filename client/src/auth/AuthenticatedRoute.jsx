// AuthenticatedRoute.jsx
import React, { useEffect, useState } from 'react';
import { Navigate, Route } from 'react-router-dom';

const AuthenticatedRoute = ({ element: Component, ...rest }) => {
  const [authenticated, setAuthenticated] = useState(false);

  useEffect(() => {
    // Check for the presence of a token (modify this logic based on your authentication mechanism)
    const token = localStorage.getItem('token');
    const isAuthenticatedUser = !!token;

    setAuthenticated(isAuthenticatedUser);
  }, []);

  return (
    <Route
      {...rest}
      render={(props) =>
        authenticated ? <Component {...props} /> : <Navigate to="/login" />
      }
    />
  );
};

export default AuthenticatedRoute;
