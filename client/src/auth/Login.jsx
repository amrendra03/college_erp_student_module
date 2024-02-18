// Login.jsx
import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

import './login.css';

const Login = ({ onLogin, setRedirectedFrom }) => {
   const navigate = useNavigate();
   const location = useLocation();
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
         // Redirect to the home page or the stored redirected route
         navigate(location.state?.from || '/');
      } else {
         alert('Invalid username or password');
      }
   };

   const loginContainerStyles = {
      width: '60%',
      margin: 'auto',
      marginTop: '100px',
      padding: '20px',
      border: '1px solid #ccc',
      borderRadius: '5px',
      boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
   };

   // return (
   //    <div style={loginContainerStyles}>
   //       <h2>Login</h2>
   //       <form onSubmit={handleFormSubmit}>
   //          <label>
   //             Username:
   //             <input
   //                type="text"
   //                name="username"
   //                value={formData.username}
   //                onChange={handleInputChange}
   //             />
   //          </label>
   //          <label>
   //             Password:
   //             <input
   //                type="password"
   //                name="password"
   //                value={formData.password}
   //                onChange={handleInputChange}
   //             />
   //          </label>
   //          <button type="submit">Login</button>
   //       </form>
   //    </div>
   // );



   return (
      <div className='login-a'>
         <div className='login-a-a'>

            <div className='login-a-icon'></div>

            <h1>College ERP</h1>

         </div>
         <div className='login-a-b'>
            <h1 className='login-ab-log'>Login</h1>
            <div className='login-ab'>
               <p className='login-p'>Email/ Roll no</p>
               <div className='login-ab-a'>
                  <div className='login-email'></div>
                  <input type="text"
                     name="username"
                     value={formData.username}
                     onChange={handleInputChange} />
               </div>
            </div>

            <div className='login-ab' style={{ height: "100px" }}>
               <p className='login-p'>Password</p>
               <div className='login-ab-a'>
                  <div className='login-password '></div>
                  <input type="password"
                     name="password"
                     value={formData.password}
                     onChange={handleInputChange} />
               </div>
               <p className='login-forget'>Forget password.</p>
            </div>

            <div className='login-ab-c' onClick={handleFormSubmit}> Login</div>

            <div className='login-ab-d'>
               <div className='login-abd-a'>
                  <span style={{ backgroundImage: "var(--auth-faculty)" }}></span>
                  <p>Faculty</p>
               </div>
               <div className='login-abd-a'>
                  <span style={{ backgroundImage: "var(--auth-student)" }}></span>
                  <p>Student</p>
               </div>
            </div>
         </div>
      </div>
   );


};

export default Login;
