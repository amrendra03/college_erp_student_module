import React, { useState } from 'react';
import Header from '../component/header/Header';
import PaymentHome from './component/PaymentHome';
import './payment.css';
const Payment = () => {


   const [paymentList, setPaymentList] = useState(["hello"]);
   const [paymentSelected, setPayment] = useState(null);


   const handlePaymentClick = (data) => {
      console.log("clicked")
      setPayment(data);
      // console.log(data)
   };

   return (
      <div className='payment'>
         <Header data={"Payment"} />
         <div className='payment-panel'>
            <div className='payment-list'>
               <p className='p'>Payment List</p>
               <div className='payment-list-container'>
                  {paymentList.map((data, index) => (
                     <div key={index} className='payment-list-item active-payment' onClick={() => handlePaymentClick(data)}>
                        <p> data={data} </p>
                     </div>
                  ))}
               </div>
            </div>
            <div className='payment-form'>
               {
                  // console.log()
                  paymentSelected != null ? <PaymentHome data={paymentSelected} /> : <div style={{ background: 'white' }} className='ex-f-n' />
               }
            </div>
         </div>
      </div>
   )
}

export default Payment
