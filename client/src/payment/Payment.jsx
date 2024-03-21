import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useCookies } from 'react-cookie';
import Header from '../component/header/Header';
import LoadingComponent from '../component/other/LoadingComponent';
import PaymentHome from './component/PaymentHome';
import './payment.css';
const Payment = () => {
   const [cookies, setCookie] = useCookies(['token', 'student']);
   const [paymentList, setPaymentList] = useState(["hello"]);
   const [paymentSelected, setPayment] = useState(null);
   const [loading, setLoading] = useState(true);


   const payment = async () => {
      try {
         console.log("Api call ... payment List")
         const response = await axios.get(`http://localhost:8085/student/payment/all/${cookies.student.rollNo}`, {
            headers: {
               'Authorization': `Bearer ${cookies.token}`
            }
         });

         let data = response.data;
         console.log(response.data)
         setPaymentList(data);
      } catch (error) {
         console.log(error);
      }
      setLoading(false);
   };

   const handlePaymentClick = (data) => {
      console.log("clicked")
      setPayment(data);
      // console.log(data)
   };

   useEffect(() => {
      const fetchData = async () => {
         try {
            payment();
         } catch (error) {
            console.log(error);
         } finally {
            setLoading(false);
         }
      };
      const timer = setTimeout(() => {
         fetchData();
      }, 200);

      return () => clearTimeout(timer);
   }, []);

   if (loading) {
      return <LoadingComponent />;
   }

   return (
      <div className='payment'>
         <Header data={"Payment"} />
         <div className='payment-panel'>
            <div className='payment-list'>
               <p className='p'>Payment List</p>
               <div className='payment-list-container'>
                  {paymentList.map((data, index) => (
                     <div key={index} className={`payment-list-item ${data.status !== 'Paid' ? `active-payment` : ''}`} onClick={() => handlePaymentClick(data)}>
                        <p> {data.name} </p>
                     </div>
                  ))}
               </div>
            </div>
            <div className='payment-form'>
               {
                  paymentSelected != null ? <PaymentHome data={paymentSelected} /> : <div style={{ background: 'white' }} className='ex-f-n' />
               }
            </div>
         </div>
      </div>
   )
}

export default Payment
