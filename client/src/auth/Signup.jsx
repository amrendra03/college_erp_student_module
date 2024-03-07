import axios from 'axios';
import React, { useState } from 'react';
import './signup.css';

const Signup = () => {
   const [formData, setFormData] = useState({
      name: '',
      email: '',
      phone: '',
      DOB: '',
      password: '',
      role: 'student',
   });
   const [confirmPassword, setConfirmPassword] = useState('');
   const [termcondition, setTermcondition] = useState(false);
   const [signupSuccess, setSignupSuccess] = useState(false);
   const [submitwarning, setSubmitmwarning] = useState({ content: '', Display: "none" });






   const [validationErrors, setValidationErrors] = useState({
      name: '',
      email: '',
      phone: '',
      DOB: '',
      password: '',
      confirmPassword: '',
   });

   const handleChange = (e) => {
      setFormData({
         ...formData,
         [e.target.name]: e.target.value,
      });
      validateField(e.target.name, e.target.value);
   };


   const validateField = (fieldName, value) => {
      let errorMessage = '';

      switch (fieldName) {
         case 'name':
            errorMessage = value.trim() === '' ? 'Name must not be blank' : '';
            break;
         case 'email':
            errorMessage = !/^\S+@\S+\.\S+$/.test(value) ? 'Invalid email format' : '';
            break;
         case 'phone':
            errorMessage = !/^\d{10}$/.test(value) ? 'Phone number must be 10 digits' : '';
            break;
         case 'DOB':
            errorMessage = value === '' ? 'Date of Birth must not be blank' : '';
            break;
         case 'password':
            errorMessage = value.trim() === '' ? 'Password must not be blank' : '';
            break;
         case 'confirmPassword':
            errorMessage = value !== formData.password ? 'Confirm password does not match with the password' : '';
            break;
         default:
            break;
      }

      setValidationErrors({
         ...validationErrors,
         [fieldName]: errorMessage,
      });
   };

   const handleSubmit = async (e) => {
      e.preventDefault();

      // Validate all fields before submission
      Object.keys(formData).forEach((fieldName) => {
         validateField(fieldName, formData[fieldName]);
      });

      validateField('confirmPassword', confirmPassword);
      // Check if there are any validation errors
      if (Object.values(validationErrors).some((error) => error !== '')) {
         alert('Please fix the validation errors before submitting the form.');
         return;
      }

      // Perform your signup logic here, e.g., send data to the server
      console.log('Form submitted:', formData);

      try {
         console.log("api calling register...")
         const url = `http://localhost:8085/auth/register`;
         const response = await axios.post(url, formData)
         // console.log(response)
         // console.log(response.data);
         if (response.status == 201) {
            setSignupSuccess(true)
         }
      } catch (e) {
         // console.log(e)
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

      <div className="signup-container">

         {signupSuccess ? (
            <div className="signup-success">
               <h2>Signup Successfully!</h2>
               <p>You have successfully signed up. Welcome!</p>
            </div>
         ) : (

            <form className="signup-form" onSubmit={handleSubmit}>
               <h2 className="signup-heading">Sign Up</h2>
               <label className="signup-label" htmlFor="name">Name:</label>
               <input className="signup-input" type="text" id="name" name="name" value={formData.name} onChange={handleChange} required />
               <span className="error-message">{validationErrors.name}</span>

               <label className="signup-label" htmlFor="email">Email:</label>
               <input className="signup-input" type="email" id="email" name="email" value={formData.email} onChange={handleChange} required />
               <span className="error-message">{validationErrors.email}</span>

               <label className="signup-label" htmlFor="phone">Phone:</label>
               <input className="signup-input" type="tel" id="phone" name="phone" pattern="\d{10}" value={formData.phone} onChange={handleChange} required />
               <span className="error-message">{validationErrors.phone}</span>

               <label className="signup-label" htmlFor="DOB">Date of Birth:</label>
               <input className="signup-input" type="date" id="DOB" name="DOB" value={formData.DOB} onChange={handleChange} required />
               <span className="error-message">{validationErrors.DOB}</span>



               <label className="signup-label" htmlFor="password">Password:</label>
               <input className="signup-input" type="password" id="password" name="password" value={formData.password} onChange={handleChange} required />
               <span className="error-message">{validationErrors.password}</span>

               <label className="signup-label" htmlFor="new-password">Confirm Password:</label>
               <input className="signup-input" type="text" id="confirm-password" name="confirm-password" value={confirmPassword} onChange={e => setConfirmPassword(e.target.value)} required />
               <span className="error-message">{validationErrors.confirmPassword}</span>

               <div className="signup-checkbox">
                  <input className='signup-checkbox-term' type="checkbox" id="agreeTerms" name="agreeTerms" checked={termcondition} onChange={e => setTermcondition(e.target.checked)} />
                  <label className='signup-term' htmlFor="agreeTerms">I agree to the terms and conditions</label>
               </div>

               <button className="signup-button" type="submit" disabled={Object.values(validationErrors).some((error) => error !== '')}>
                  Sign Up
               </button>
               <p className='login-p' style={{ fontSize: "18px", color: "red", display: `${submitwarning.Display}`, marginTop: "4px", textAlign: "center" }}>{submitwarning.content}</p>

               <p className='login-p' style={{ fontSize: "14px", marginTop: "30px", textAlign: "center" }}>Your all data will be safe as per term and condition <br />@ walk withus toward the future</p>



            </form>
         )}
      </div>
   );
};

export default Signup;
