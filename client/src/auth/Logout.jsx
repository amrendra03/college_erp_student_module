import React from 'react';
import './logout.css';

const Logout = () => {
   return (
      <div className='logout-container'>
         <div className='logout-a'>
            <h2>Logout Successful</h2>
            <p>You have been successfully logged out. Thank you for using our application.</p>
            {/* You can add more content or customize the component as needed */}
         </div>
      </div>
   );
};

export default Logout;
