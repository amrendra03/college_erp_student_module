import React from 'react';

import './nav.css';

const Nav = () => {
   return (
      <div className="Nav">
         <div className="Frame3" >
         </div>
         <div className='nav-c1'>

            <div className="Rectangle5  nav-txt" >
               <div className="MaterialSymbolsDashboard" >
               </div>
               Dashboard
            </div>
            < div className='Rectangle5 nav-txt'>
               <div className="IconParkDegreeHat" />
               Course
            </div>

            < div className='Rectangle5 nav-txt'>
               <div className="HealthiconsIExamMultipleChoice" />
               Exam
            </div>

            < div className='Rectangle5 nav-txt'>
               <div className="FaSolidUserFriends" />
               Faculty

            </div>

            <div className='Rectangle5 nav-txt'>
               <div className="Group" />
               Payment
            </div>


         </div>

         <div className='nav-c2'>
            < div className='Rectangle5 nav-txt'>
               <div className="MajesticonsLogout" />
               Logout
            </div>
            < div className='Rectangle5 nav-txt'>
               <div className="IconParkSolidSettingTwo" />
               Setting
            </div>
         </div>
      </div>
   )
}

export default Nav
