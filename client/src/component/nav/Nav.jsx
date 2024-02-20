import React from 'react';

import { NavLink } from 'react-router-dom';
import './nav.css';

const Nav = ({ authenticated, onLogout }) => {
   return (
      <div className={authenticated ? 'Nav' : 'Nav navauuthset'} >
         <div className="Frame3" >
         </div>
         <div className='nav-c1'>

            <NavLink to="/" className="Rectangle5  nav-txt" >
               <div className="MaterialSymbolsDashboard" />

               Dashboard
            </NavLink>
            <NavLink to="/course" className='Rectangle5 nav-txt'>
               <div className="IconParkDegreeHat" />
               Course
            </NavLink>

            < NavLink to="/exam" className='Rectangle5 nav-txt'>
               <div className="HealthiconsIExamMultipleChoice" />
               Exam
            </NavLink>

            < NavLink to="faculty" className='Rectangle5 nav-txt'>
               <div className="FaSolidUserFriends" />
               Faculty
            </NavLink>

            <NavLink to="payment" className='Rectangle5 nav-txt'>
               <div className="Group" />
               Payment
            </NavLink>

         </div>

         <div className='nav-c2'>
            {
               authenticated ?
                  <NavLink to="/logout" className='Rectangle5 nav-txt' onClick={onLogout}>
                     <div className="MajesticonsLogout" />
                     Logout
                  </NavLink> : <></>
            }
            <NavLink to="/setting" className='Rectangle5 nav-txt'>
               <div className="IconParkSolidSettingTwo" />
               Setting
            </NavLink>
         </div>
      </div>
   )
}

export default Nav
