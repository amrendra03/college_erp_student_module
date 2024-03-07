import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
import React, { useEffect, useState } from 'react';
import { useCookies } from 'react-cookie';
import './login.css';

const Login = ({ onLogin, setRedirectedFrom }) => {
   const [cookies, setCookie] = useCookies(['token']);
   const [formData, setFormData] = useState({
      username: '',
      password: '',
   });
   const [showForgetPasswordForm, setShowForgetPasswordForm] = useState(false);
   const [showResetPassword, setShowResetPassword] = useState(false);
   const [showSuccessfullyCode, setSuccessfullyCode] = useState(false);

   const [forgetPasswordEmail, setForgetPasswordEmail] = useState('');
   const [otpcode, setOtpcode] = useState('');
   const [newPassword, setNewPassword] = useState('');
   const [confirmPassword, setConfirmPassword] = useState('');



   const [emailwarning, setEmailwarning] = useState({ content: '', Display: "none" });
   const [otpwarning, setOtpwarning] = useState({ content: '', Display: "none" });
   const [newpasswordwarning, setNewPasswordwarning] = useState({ content: '', Display: "none", color: "gray" });
   const [confirmwarning, setConfirmwarning] = useState({ content: '', Display: "none" });
   const [submitwarning, setSubmitmwarning] = useState({ content: '', Display: "none" });
   const [loginerror, setLoginerror] = useState({ content: '', Display: "none" });

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
         const decodedToken = jwtDecode(token);
         const expirationTimeInSeconds = decodedToken.exp;
         setCookie('token', token, {
            path: '/',
            expires: new Date(expirationTimeInSeconds * 1000),
         });
         onLogin();
         // Redirect to the initial route or '/' if not specified
         setRedirectedFrom(location.state?.from || '/');
      } catch (e) {
         // console.log(e);
         if (e.code === "ERR_NETWORK") {
            setLoginerror({
               content: e.code,
               Display: "block"
            });
         } else {
            // console.log(e.response)
            setLoginerror({
               content: e.response.data.token,
               Display: "block"
            });

            // console.log(loginerror.content)
         }
      }
   };

   useEffect(() => {
      // Set the redirected route when the component mounts
      setRedirectedFrom(location.state?.from || '/');
   }, [location.state, setRedirectedFrom]);

   const handleForgetPasswordSubmit = async (e) => {
      e.preventDefault();

      if (forgetPasswordEmail === "") {
         setEmailwarning({
            content: "please enter the valid email.",
            Display: "block"
         });
         // return;
      }

      try {
         // console.log("sending request.. api")

         const url = `http://localhost:8085/auth/send-otp?email=`;
         const response = await axios.post(url + forgetPasswordEmail);
         // console.log(response.data);

         if (response.data.success == true) {
            setShowForgetPasswordForm(false);
            // setShowVerifyCode(true);
            setShowResetPassword(true);
         } else {
            setOtpsend(response.data.message);
            // console.log(response.data.message);
         }

      } catch (e) {
         console.log(e.code)
         if (e.code === "ERR_NETWORK") {
            setEmailwarning({
               content: e.code,
               Display: "block"
            });
         } else {
            setEmailwarning({
               content: e.response.data.message,
               Display: "block"
            });
         }
         // console.log(e.response.data)
      }
      // setShowForgetPasswordForm(false);
      // // setShowVerifyCode(true);
      // setShowResetPassword(true);
   };

   const validatePassword = (password) => {
      // Define your password criteria here
      const minLength = 8;
      const hasUppercase = /[A-Z]/.test(password);
      const hasLowercase = /[a-z]/.test(password);
      const hasNumber = /\d/.test(password);

      // Check if the password meets all criteria
      if (password.length < minLength) {
         return 'Password must be at least 8 characters long.';
      }

      if (!hasUppercase) {
         return 'Password must contain at least one uppercase letter.';
      }

      if (!hasLowercase) {
         return 'Password must contain at least one lowercase letter.';
      }

      if (!hasNumber) {
         return 'Password must contain at least one number.';
      }

      // If the password passes all criteria, return null (indicating success)
      return "";
   };

   const handleResetPasswordSubmit = async (e) => {
      // console.log("function call.")
      e.preventDefault();
      if (otpcode === "") {
         console.log("otp: ", otpcode);
         setOtpwarning({
            content: "Please enter the OTP.",
            Display: "block",
         });
         return;
      }

      setOtpwarning({
         content: "",
         Display: "none",
      });

      if (newPassword === "") {
         console.log("newPassword: ", newPassword);
         setNewPasswordwarning({
            content: "Please enter the new password.",
            Display: "block",
         });
         return;
      }

      const newPasswordValidationMessage = validatePassword(newPassword);
      if (newPasswordValidationMessage !== "") {
         setNewPasswordwarning({
            content: newPasswordValidationMessage,
            Display: "block",
         });
         return;
      }

      setNewPasswordwarning({
         content: "",
         Display: "none",
      });

      if (newPassword !== confirmPassword) {
         setConfirmwarning({
            content: "Confirm password does not match with the new password.",
            Display: "block",
         });
         return;
      }

      setConfirmwarning({
         content: "",
         Display: "none",
      });

      try {
         // console.log("submit on api... reset password");
         const url = `http://localhost:8085/auth/forget_password`;
         const response = await axios.post(url, {
            email: forgetPasswordEmail,
            otp: otpcode,
            password: confirmPassword,
         });
         // console.log(response.data);
         setShowForgetPasswordForm(false);
         setShowResetPassword(false);
         setSuccessfullyCode(true);
         // console.log('Password reset successful!');
      } catch (e) {
         // console.log(e);
         // console.log("error---.")
         // console.log(e.code)
         if (e.code === "ERR_NETWORK") {
            setSubmitmwarning({
               content: e.code,
               Display: "block"
            });
         } else {
            // console.log(e.response)
            setSubmitmwarning({
               content: e.response.data.message,
               Display: "block"
            });
         }
         // console.log(e.response)
      }
   };

   return (
      <div className='login-a'>
         <div className='login-a-a'>
            <div className='login-a-icon'></div>
            <h1>College ERP</h1>
         </div>
         {!showForgetPasswordForm && !showSuccessfullyCode && !showResetPassword && (
            <form onSubmit={handleFormSubmit} className='login-a-b'>
               <h1 className='login-ab-log'>Login</h1>
               <div className='login-ab'>
                  <p className='login-p'>Email / Roll no</p>
                  <div className='login-ab-a'>
                     <div className='login-email'></div>
                     <input
                        type="text"
                        name="username"
                        value={formData.username}
                        onChange={handleInputChange}
                        required
                     />
                  </div>
               </div>

               <div className='login-ab' style={{ height: "80px" }}>
                  <p className='login-p'>Password</p>
                  <div className='login-ab-a'>
                     <div className='login-password'></div>
                     <input
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleInputChange}
                        required
                     />
                  </div>
                  <p className='login-forget' onClick={() => setShowForgetPasswordForm(true)}>
                     Forget password.
                  </p>
               </div>

               <button className='login-ab-c' type="submit">
                  Login
               </button>
               <p className='login-p' style={{ marginTop: "2px", fontSize: "15px", color: "red", display: `${loginerror.Display}` }}>{loginerror.content}</p>
               <div className='login-ab-d' >
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
         )}

         {showForgetPasswordForm && (
            <form onSubmit={handleForgetPasswordSubmit} className='login-a-b'>
               <h1 className='login-ab-log'>Forget Password</h1>
               <div className='login-ab' style={{ height: "auto", marginBottom: "10px" }}>
                  <p className='login-p'>Email / Roll no</p>
                  <div className='login-ab-a'>
                     <div className='login-email'></div>
                     <input
                        type="text"
                        name="forgetPasswordEmail"
                        value={forgetPasswordEmail}
                        onChange={(e) => setForgetPasswordEmail(e.target.value)}
                        required
                     />
                  </div>
                  <p className='login-p' style={{ fontSize: "12px", color: "red", display: `${emailwarning.Display}` }}>{emailwarning.content}</p>
               </div>
               <button className='login-ab-c' type="submit">Submit</button>
               <p className='login-forget' onClick={() => setShowForgetPasswordForm(false)}>
                  Go back to Login
               </p>
            </form>
         )}

         {showResetPassword && (
            <form onSubmit={handleResetPasswordSubmit} className='login-a-b' style={{ marginBottom: "10px", height: "450px" }}>
               <h1 className='login-ab-log' style={{ marginBottom: '10px', marginTop: "10px" }}>Reset Password</h1>
               <div className='login-ab' style={{ height: "auto", marginBottom: "8px" }}>
                  <p className='login-p'>Email / Roll no</p>
                  <div className='login-ab-a'>
                     <div className='login-email'></div>
                     <input type="text" name="forgetPasswordEmail" value={forgetPasswordEmail} onChange={(e) => setForgetPasswordEmail(e.target.value)} readOnly />
                  </div>
                  {/* <p className='login-p' style={{ fontSize: "12px", color: "red" }}>Enter the email</p> */}
               </div>
               <div className='login-ab' style={{ height: "auto", marginBottom: "8px" }}>
                  <p className='login-p'>OTP</p>
                  <div className='login-ab-a'>
                     <input
                        type="text"
                        name="resetCode"
                        value={otpcode}
                        onChange={(e) => setOtpcode(e.target.value)}
                        style={{ borderRadius: "7px", borderLeft: "1px solid rgb(71, 71, 71)" }} />
                  </div>
                  <p className='login-p' style={{ fontSize: "12px", color: "red", display: `${otpwarning.Display}` }}>{otpwarning.content}</p>
               </div>
               <div className='login-ab' style={{ height: "auto", marginBottom: "8px" }}>
                  <p className='login-p'>New Password</p>
                  <div className='login-ab-a'>
                     <input
                        type="password"
                        name="new-password"
                        value={newPassword}
                        onChange={(e) => setNewPassword(e.target.value)}
                        style={{ borderRadius: "7px", borderLeft: "1px solid rgb(71, 71, 71)" }}
                     />
                  </div>
                  <p className='login-p' style={{ fontSize: "12px", color: "red", display: `${newpasswordwarning.Display}` }}>{newpasswordwarning.content}</p>
               </div>
               <div className='login-ab' style={{ height: "auto", marginBottom: "10px" }}>
                  <p className='login-p'>Confirm Password</p>
                  <div className='login-ab-a'>
                     <input
                        type="text"
                        name="confirmPassword"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        style={{ borderRadius: "7px", borderLeft: "1px solid rgb(71, 71, 71)" }}
                     />
                  </div>
                  <p className='login-p' style={{ fontSize: "12px", color: "red", display: `${confirmwarning.Display}` }}>{confirmwarning.content}</p>
               </div>
               <button className='login-ab-c' type="submit">Reset Password</button>
               <p className='login-p' style={{ fontSize: "12px", color: "red", display: `${submitwarning.Display}`, margin: "4px 0px" }}>{submitwarning.content}</p>
               <p className='login-forget' onClick={() => setShowResetPassword(false)}>
                  Go back to Login
               </p>
            </form>
         )}

         {showSuccessfullyCode && (
            // <form onSubmit={handleSuccessfullyResetPassword} className='login-a-b'>
            <div className='login-a-b'>
               <h1 className='login-ab-log' style={{ textAlign: "center" }}>Password successfully reset!</h1>
               <p className='login-p' style={{ textAlign: "center", margin: "0 4px" }}>
                  The password has been reset successfully for the account registered with email
                  <span style={{ color: 'black' }}> {forgetPasswordEmail}</span>.
               </p>
               <p className='login-forget' style={{ color: "blue" }} onClick={() => setSuccessfullyCode(false)}>
                  Go back to Login
               </p>
            </div>
         )}
      </div>
   );
};

export default Login;
