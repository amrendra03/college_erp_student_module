// Login.jsx
import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
import { useCookies } from 'react-cookie';
import './login.css';

const Login = ({ onLogin, setRedirectedFrom }) => {

   const navigate = useNavigate();
   const location = useLocation();
   const [cookies, setCookie] = useCookies(['token']);
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


   const handleFormSubmit = async (e) => {
      e.preventDefault();

      try {
         const response = await axios.post("http://localhost:8085/auth/login", {
            username: formData.username,
            password: formData.password,
         });

         const token = response.data.token;
         console.log("login token" + token)
         const decodedToken = jwtDecode(token);
         const expirationTimeInSeconds = decodedToken.exp;

         // console.log("Received token from the server:", token);

         setCookie('token', token, {
            path: '/',
            expires: new Date(expirationTimeInSeconds * 1000),
         });

         // console.log("Token saved in cookies:", document.cookie);


         // Redirect to the home page or the stored redirected route
         onLogin();
         navigate(location.state?.from || '/');

      } catch (e) {
         console.log(e);
         alert("Server error 500")
      }
   };

   useEffect(() => {
      // Set the redirected route when the component mounts
      setRedirectedFrom(location.state?.from || '/');
   }, [location.state, setRedirectedFrom]);


   return (
      <div className='login-a'>
         <div className='login-a-a'>

            <div className='login-a-icon'></div>

            <h1>College ERP</h1>

         </div>
         {/* <div className='login-a-b'> */}

         <form onSubmit={handleFormSubmit} className='login-a-b'>
            <h1 className='login-ab-log'>Login</h1>
            <div className='login-ab'>
               <p className='login-p'>Email / Roll no</p>
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

            <button className='login-ab-c' type="submit">Login</button>
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
         </form>

         {/* </div> */}
      </div>
   );


};

export default Login;
