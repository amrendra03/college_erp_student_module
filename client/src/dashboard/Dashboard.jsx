import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useCookies } from 'react-cookie';
import Doughnut from '../component/chart/Doughnut';
import Header from '../component/header/Header';
import CalendarC from '../component/other/Calendar';
import { API } from '../config/env';
import './dashboard.css';

const Dashboard = () => {

   const [cookies, setCookie] = useCookies(['token', 'student']);

   const [student, setStudent] = useState({});

   const [status, setStatus] = useState([])

   // const data = [
   //    { subject: 'Math', faculty: 'Dr. Smith', present: 8, absent: 2, totalAssignment: 15, submitted: 12, notSubmitted: 3 },
   //    { subject: 'English', faculty: 'Prof. Johnson', present: 9, absent: 1, totalAssignment: 12, submitted: 10, notSubmitted: 2 },
   //    { subject: 'Science', faculty: 'Dr. Brown', present: 7, absent: 3, totalAssignment: 10, submitted: 8, notSubmitted: 2 },
   //    { subject: 'History', faculty: 'Prof. Davis', present: 6, absent: 4, totalAssignment: 8, submitted: 7, notSubmitted: 1 },
   //    { subject: 'Geography', faculty: 'Dr. Wilson', present: 8, absent: 2, totalAssignment: 12, submitted: 9, notSubmitted: 3 },
   //    { subject: 'Computer Science', faculty: 'Prof. Miller', present: 7, absent: 3, totalAssignment: 10, submitted: 8, notSubmitted: 2 },
   //    { subject: 'Art', faculty: 'Dr. Turner', present: 9, absent: 1, totalAssignment: 15, submitted: 12, notSubmitted: 3 },
   //    { subject: 'Physical Education', faculty: 'Prof. Clark', present: 8, absent: 2, totalAssignment: 12, submitted: 10, notSubmitted: 2 },
   //    { subject: 'Music', faculty: 'Dr. Harris', present: 6, absent: 4, totalAssignment: 8, submitted: 7, notSubmitted: 1 },
   //    { subject: 'Language', faculty: 'Prof. Robinson', present: 7, absent: 3, totalAssignment: 10, submitted: 8, notSubmitted: 2 },
   // ];

   const statusCall = async () => {

      console.log("STATUS.......")
      // console.log("Token get from cookies: " + cookies.token)
      const student = cookies.student;
      const rollNo = student.rollNo;
      // console.log(student);
      // console.log(rollNo)
      try {

         const response = await axios.get(`${API.status}/${rollNo}`, {
            withCredentials: true,
            headers: {
               'Authorization': `Bearer ${cookies.token}`
            }
         });
         // console.log("Student Status:")
         console.log(response.data);
         setStatus(response.data)

      } catch (error) {
         console.log(error)
      }
      console.log("Status exite.")
   }
   const studentDetail = async () => {
      console.log("Student DETAIL.........")
      // console.log("Token get from cookies: " + cookies.token)
      try {
         const response = await axios.get(API.studentDetail, {
            headers: {
               'Authorization': `Bearer ${cookies.token}`
            }
         })


         // console.log(response.data);
         setStudent(response.data);
         // console.log("Student detail:");
         console.log(student);
         setCookie('student', response.data, { path: '/' });
         // console.log(student.rollNo)
         statusCall();

      } catch (error) {
         console.log(error)
      }
      console.log("Student Detail exit.")

   }


   useEffect(() => {
      studentDetail();
   }, [])



   const notifi = [
      { message: "Final exam date", active: 0 },
      { message: "Project submission deadline", active: 0 },
      { message: "Club meeting tomorrow", active: 0 },
      { message: "Library closed for renovations", active: 0 },
      { message: "Guest lecture on Friday", active: 0 },
      { message: "Reminder: Pay tuition fees", active: 1 },
      { message: "Sports day announcement", active: 1 },
      { message: "Holiday schedule update", active: 1 },
      { message: "Career fair next week", active: 1 },
      { message: "Book return deadline", active: 1 },
   ];



   return (
      <div className='dashboard' >
         <Header data={"Dashboard"} />
         <div className='ds-1'>
            <div className="Status1 display" >
               <div className='st-icon' />
               <div>
                  <span className='txt'>{student.name}</span><br />
                  <span className='txt'>{student.rollNo}</span>
               </div>
            </div>
            <div className="Status2 display" >
               <div className='st-icon-2' />
               <div>
                  <span className='txt'>B.Tech CSE( AI &ML)</span><br />
                  <span className='txt'>sem 7th</span>
               </div>
            </div>
            <div className="Status3 display" >
               <div className='st-icon-3' />
               <div>
                  <span className='txt'>Fee</span><br />
                  <span className='txt'>1,00,000</span>
               </div>
            </div>
            <div className="Status4 display" >
               <div className='st-icon-4' />
               <div>
                  <span className='txt'>Fine</span><br />
                  <span className='txt'>1,00,000</span>
               </div></div>
            <div className="Status5 display" >
               <div className='st-icon-5' />
               <div>
                  <span className='txt'>Comptetive Score</span><br />
                  <span className='txt'>26546465688798</span>
               </div>
            </div>
         </div>
         <div className='ds-2'>
            <div className='ds-3'>
               <div className='ds-5'>
                  <div className='thead'>
                     <div className='cl-1' style={{ width: 160 }}>Subject</div>
                     <div className='cl-1' style={{ width: 150 }}>Faculty</div>
                     {/* <div className='cl-1' style={{ width: 50 }}>Total</div> */}
                     <div className='cl-1' style={{ width: 70 }}>Present</div>
                     <div className='cl-1' style={{ width: 70 }}>Absent</div>
                     <div className='cl-1' style={{ width: 120 }}>Total Assingment</div>
                     <div className='cl-1' style={{ width: 90 }}>Submitted</div>
                     <div className='cl-1' style={{ width: 110, border: 'none' }}>Not Submitted</div>
                  </div>
                  <div className='ds-6' id='t-row' >
                     {status.map((item, index) => (
                        <Row key={index} data={item} />
                     ))}
                  </div>
               </div>
               <div className='notifi-1'>
                  <div className='notifi-2'>
                     <span style={{ marginLeft: 10 }}>
                        Notification
                     </span>
                  </div>
                  <div className='notifi-3'>
                     {notifi.map((item, index) => (
                        <Notifi key={index} data={item} />
                     ))}
                  </div>
               </div>
            </div>
            <div className='ds-4'>

               <div className='ds-hex'>
                  <Doughnut style={{ borderRadius: 10 }} />
               </div>
               {/* <div className='ds-cal'>
               </div> */}
               <CalendarC />

            </div>
         </div>

      </div>
   )
}


const Row = ({ data }) => {
   return (
      <div className='ds-6-row-1'>
         <div className='row-1' style={{ width: 160 }}>{data.subject}</div>
         <div className='row-1' style={{ width: 150 }}>{data.facultyName}</div>
         <div className='row-1' style={{ width: 70 }}>{data.present}</div>
         <div className='row-1' style={{ width: 70 }}>{data.absent}</div>
         <div className='row-1' style={{ width: 120 }}>{data.submitted + data.notSubmitted}</div>
         <div className='row-1' style={{ width: 90 }}>{data.submitted}</div>
         <div className='row-1' style={{ width: 110, border: 'none' }}>{data.notSubmitted}</div>
      </div>
   );
};

const Notifi = ({ data }) => {
   const notifiClass = data.active === 1 ? 'notifi-icon-img-d' : 'notifi-icon-img-h';
   const notirow = data.active === 1 ? 'notifi-row-color' : '';
   const notimess = data.active === 1 ? 'notifi-row-mess' : '';

   return (
      <>
         <div className={`notifi-row ${notirow}`}>
            <div className={`notifi-icon ${notifiClass}`} />
            <div className={`notifi-text ${notimess}`}>{data.message}</div>
         </div>
      </>
   )

}


export default Dashboard
