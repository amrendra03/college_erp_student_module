// LoadingComponent.jsx

import React from 'react';

const LoadingComponent = () => {
   return (
      <div style={loadingContainerStyle}>
         <div style={loadingInnerStyle}></div>
      </div>
   );
};

const loadingContainerStyle = {
   position: 'fixed',
   top: 0,
   left: 0,
   width: '100%',
   height: '100%',
   display: 'flex',
   justifyContent: 'center',
   alignItems: 'center',
   zIndex: 9999,
   backgroundColor: 'rgba(255, 255, 255, 1)', // Semi-transparent white
};



const loadingInnerStyle = {
   border: '4px solid #3498db', // Blue border
   borderRadius: '50%',
   width: '20px',
   height: '20px',
};

export default LoadingComponent;
