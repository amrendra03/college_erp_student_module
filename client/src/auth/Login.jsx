// Login.jsx
import React, { useState } from 'react';

const Login = ({ onLogin }) => {
   const [formData, setFormData] = useState({
      username: '',
      password: '',
   });

   const handleInputChange = (e) => {
      setFormData({
         ...formData,
         [e.target.name]: e.target.value,
      });
   };

   const handleFormSubmit = (e) => {
      e.preventDefault();
      // Perform authentication logic here (e.g., API call, validation)
      // For simplicity, we'll just check if the username and password are 'user'
      if (formData.username === 'user' && formData.password === 'user') {
         // Simulate a token (you would get this from your authentication server)
         const token = 'some-random-token';
         // Store the token in local storage
         localStorage.setItem('token', token);
         // Notify the parent component about successful login
         onLogin();
      } else {
         alert('Invalid username or password');
      }
   };

   const loginContainerStyles = {
      width: '60%', // Set the width to 60% of the container
      margin: 'auto', // Center the login box horizontally
      marginTop: '100px', // Adjust the top margin as needed
      padding: '20px',
      border: '1px solid #ccc',
      borderRadius: '5px',
      boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
   };

   return (
      <div style={loginContainerStyles}>
         <h2>Login</h2>
         <form onSubmit={handleFormSubmit}>
            <label>
               Username:
               <input
                  type="text"
                  name="username"
                  value={formData.username}
                  onChange={handleInputChange}
               />
            </label>
            <label>
               Password:
               <input
                  type="password"
                  name="password"
                  value={formData.password}
                  onChange={handleInputChange}
               />
            </label>
            <button type="submit">Login</button>
         </form>
      </div>
   );
};

export default Login;
