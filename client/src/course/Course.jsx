import React from 'react';
import Header from '../component/header/Header';
import './course.css';
const Course = () => {

   const data = {
      name: 'Amrendra Yadav ',
      phoneno: 9335349251,
      course: "B.Tech CSE(AI & ML)",
      rollno: 2004281530002,
      registrationdate: "24/11/2020",
      enrollno: 123546564656756,
      totalsubject: 48,
      duration: "4year",
      totalsem: 8,
      completed: 6,
      current: 7,
   }


   return (
      <div className='course'>
         <Header data={"Course"} />

         <div className="stc">
            <div className="pic" />
            <p className="stc-name">
               Name: {data.name}
               <br />
               Phone no: {data.phoneno}
            </p>
            <p className="stc-course">
               <span className="text-wrapper">Course:</span>
               <span className="span">
                  {" "}
                  {data.course}
                  <br />
               </span>
               <span className="text-wrapper">Registration Date:</span>
               <span className="span">
                  {" "}
                  {data.registrationdate}
                  <br />
               </span>
               <span className="text-wrapper">Enroll no:</span>
               <span className="span">
                  {" "}
                  {data.enrollno}
                  <br />
               </span>
               <span className="text-wrapper">Roll no:</span>
               <span className="span"> {data.rollno}</span>
            </p>
            <p className="total-sem">
               <span className="text-wrapper-2">Total sem:</span>
               <span className="span">
                  {" "}
                  {data.totalsem}<br />
               </span>
               <span className="text-wrapper-2">Completed:</span>
               <span className="span">
                  {" "}
                  {data.completed}<br />
               </span>
               <span className="text-wrapper-2">Current:</span>
               <span className="span"> {data.current}</span>
            </p>
            <p className="total-subject">
               <span className="text-wrapper-3">Total Subject:</span>
               <span className="span">
                  {" "}
                  {data.totalsubject}
                  <br />
               </span>
               <span className="text-wrapper-3">Duration:</span>
               <span className="span"> {data.duration}</span>
            </p>
         </div>

         <div className='course-panel'>
            <div className='course-panel-1'>
               <div className='cors-list'>
                  <p className='cors-list-2'>Register Course</p>
                  <div>

                     <CourseLIst />

                  </div>
               </div>
               <div className='cors-faculty'>
                  <p className='cors-list-2'>Faculty List</p>
                  <div>
                     <FacultyList />
                  </div>
               </div>
            </div>
            <div className='course-time-table'>
               <p className='ctt-n'>B. Tech CSE (AI & ML) Session 2023-24 sem 7th</p>
               <table >
                  <thead>
                     <tr style={{ backgroundColor: '#313132' }}>
                        <th style={{ background: '#313132' }}></th>
                        <th>9:00-10:00</th>
                        <th>10:00-11:00</th>
                        <th>11:00-12:00</th>
                        <th>12:00-1:00</th>
                        <th>1:00-2:00</th>
                        <th>2:00-3:00</th>
                        <th>3:00-4:00</th>
                     </tr>
                  </thead>
                  <tbody>
                     <tr>
                        <td>Monday</td>
                        <td>Row 1, Cell 2</td>
                        <td>Row 1, Cell 3</td>
                        <td>Row 1, Cell 4</td>
                        <td>Row 1, Cell 5</td>
                        <td>Row 1, Cell 6</td>
                        <td>Row 1, Cell 7</td>
                        <td>Row 1, Cell 8</td>

                     </tr>
                     <tr>
                        <td>Tuesday</td>
                        <td>Row 2, Cell 2</td>
                        <td>Row 2, Cell 3 </td>
                        <td>Row 2, Cell 4</td>
                        <td>Row 2, Cell 5</td>
                        <td>Row 2, Cell 6</td>
                        <td>Row 2, Cell 7</td>
                        <td>Row 2, Cell 8</td>
                     </tr>
                     <tr>
                        <td>Wednesday</td>
                        <td>Row 3, Cell 2</td>
                        <td>Row 3, Cell 3 </td>
                        <td>Row 3, Cell 4</td>
                        <td>Row 3, Cell 5</td>
                        <td>Row 3, Cell 6</td>
                        <td>Row 3, Cell 7</td>
                        <td>Row 3, Cell 8</td>
                     </tr>
                     <tr>
                        <td>Thursday</td>
                        <td>Row 4, Cell 2</td>
                        <td>Row 4, Cell 3 </td>
                        <td>Row 4, Cell 4</td>
                        <td>Row 4, Cell 5</td>
                        <td>Row 4, Cell 6</td>
                        <td>Row 4, Cell 7</td>
                        <td>Row 4, Cell 8</td>
                     </tr>
                     <tr>
                        <td>Friday</td>
                        <td>Row 5, Cell 2</td>
                        <td>Row 5, Cell 3 </td>
                        <td>Row 5, Cell 4</td>
                        <td>Row 5, Cell 5</td>
                        <td>Row 5, Cell 6</td>
                        <td>Row 5, Cell 7</td>
                        <td>Row 5, Cell 8</td>
                     </tr>
                     <tr>
                        <td>Saturday</td>
                        <td>Row 6, Cell 2</td>
                        <td>Row 6, Cell 3 </td>
                        <td>Row 6, Cell 4</td>
                        <td>Row 6, Cell 5</td>
                        <td>Row 6, Cell 6</td>
                        <td>Row 6, Cell 7</td>
                        <td>Row 6, Cell 8</td>
                     </tr>
                     <tr>
                        <td style={{ borderRadius: '0 0 0 10px' }}>Sunday</td>
                        <td>Row 7, Cell 2</td>
                        <td>Row 7, Cell 3 </td>
                        <td>Row 7, Cell 4</td>
                        <td>Row 7, Cell 5</td>
                        <td>Row 7, Cell 6</td>
                        <td>Row 7, Cell 7</td>
                        <td style={{ borderRadius: '0 0 10px 0' }}>Row 7, Cell 8</td>
                     </tr>
                  </tbody>
               </table>
            </div>
         </div>
      </div>
   )
}

const CourseLIst = ({ data }) => {
   return (
      <div className="course-list-name">
         <div className="course-list-name-1">
            B. Tech CSE( AI &amp; ML)
         </div>
      </div>
   );
};

const FacultyList = () => {
   return (
      <div style={{ width: '100%', height: '47px', display: 'flex', alignItems: 'center' }}>

         <div style={{ background: "black", borderRadius: '5rem', height: '40px', width: '40px', marginLeft: '4px' }}></div>
         <div className='cors-fact-name'> Ram yadav <br /><span style={{ color: '#0094FF' }}>Sub: </span> Data Structure</div>

      </div>
   )
}

export default Course




