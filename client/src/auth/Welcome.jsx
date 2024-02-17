import React from 'react';

const Welcome = () => {
   const welcomeStyles = {
      textAlign: 'center',
      marginTop: '20px',
      padding: '20px',
      border: '1px solid #ccc',
      borderRadius: '5px',
      boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
   };

   return (
      <div style={welcomeStyles}>
         <h2>Welcome to the Dashboard!</h2>
         <p>This is your secure area.</p>
         <p>Feel free to explore the features available to you.</p>
      </div>
   );
};

export default Welcome
