import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useCookies } from 'react-cookie';
import Header from '../component/header/Header';
import LoadingComponent from '../component/other/LoadingComponent';
import './course.css';


const Course = () => {
   const [cookies, setCookie] = useCookies(['token', 'student']);
   const [studentData, setStudentData] = useState({});
   const [loading, setLoading] = useState(true);

   const studentDataFetching = async () => {
      // e.preventDefault();
      try {
         // console.log("Api call  Student data... course")
         const response = await axios.get(`http://localhost:8085/student/data`, {
            headers: {
               'Authorization': `Bearer ${cookies.token}`
            }
         });

         let data = response.data;
         data.registrationDate = formatDateTime(data.registrationDate);
         setStudentData(data);
         // console.log(response.data)
      } catch (error) {
         console.log(error);
      }
      setLoading(false);  // Set loading to false after API call
   };

   function formatDateTime(inputDateTime) {
      const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
      const formattedDate = new Date(inputDateTime).toLocaleDateString('en-GB', options);
      return formattedDate;
   }

   useEffect(() => {
      const fetchData = async () => {
         try {
            await studentDataFetching();
         } catch (error) {
            console.log(error);
         } finally {
            // Set loading to false after API calls
            setLoading(false);
         }
      };

      // Simulating a 3-second interval before showing the main content
      const timer = setTimeout(() => {
         fetchData();
      }, 200);

      return () => clearTimeout(timer);
   }, []); // Added dependency to useEffect

   if (loading) {
      return <LoadingComponent />;
   }

   return (
      <div className='course'>
         <Header data={"Course"} />

         <div className="stc">
            <div className="pic" />
            <p className="stc-name">
               <span style={{ color: '#494949', fontSize: '12px' }}>Name:</span> <span style={{ color: '#1f1f1f' }}>{studentData.name}</span>
               <br />
               <span style={{ color: '#494949', fontSize: '12px' }}>Phone no:</span> <span style={{ color: '#1f1f1f' }}> {studentData.phoneNo}</span>
            </p>
            <p className="stc-course">
               <span className="text-wrapper">Course:</span>
               <span className="span">
                  {" "}
                  {studentData.course}
                  <br />
               </span>
               <span className="text-wrapper">Registration Date:</span>
               <span className="span">
                  {" "}
                  {studentData.registrationDate}
                  <br />
               </span>
               <span className="text-wrapper">Enroll no:</span>
               <span className="span">
                  {" "}
                  {studentData.enrollNo}
                  <br />
               </span>
               <span className="text-wrapper">Roll no:</span>
               <span className="span"> {studentData.rollNo}</span>
            </p>
            <p className="total-sem">
               <span className="text-wrapper-2">Total sem:</span>
               <span className="span">
                  {" "}
                  {studentData.totalSem}<br />
               </span>
               <span className="text-wrapper-2">Completed:</span>
               <span className="span">
                  {" "}
                  {studentData.completed}<br />
               </span>
               <span className="text-wrapper-2">Current:</span>
               <span className="span"> {studentData.current}</span>
            </p>
            <p className="total-subject">
               <span className="text-wrapper-3">Total Subject:</span>
               <span className="span">
                  {" "}
                  {studentData.totalSubject}
                  <br />
               </span>
               <span className="text-wrapper-3">Duration:</span>
               <span className="span"> {studentData.duration}</span>
            </p>
         </div>

         <div className='course-panel'>
            <div className='course-panel-1'>
               <div className='cors-list'>
                  <p className='cors-list-2'>Register Course</p>
                  <div>

                     <CourseLIst data={studentData} />

                  </div>
               </div>
               <div className='cors-faculty'>
                  <p className='cors-list-2'>Faculty List</p>

                  <FacultyList data={studentData} />

               </div>
            </div>
            <div className='course-time-table'>
               <p className='ctt-n'>B. Tech CSE (AI & ML) Session 2023-24 sem 7th</p>
               <TimeTable data={studentData} />
            </div>
         </div>
      </div>
   )
}

const CourseLIst = ({ data }) => {
   return (
      <div className="course-list-name">
         <div className="course-list-name-1">
            {data.course}
         </div>
      </div>
   );
};

const FacultyList = ({ data }) => {


   const [cookies, setCookie] = useCookies(['token', 'student']);


   const [facultyData, setData] = useState({});


   const getFacultyList = async () => {

      try {
         // console.log("time table api called")
         // console.log(data.courseId)
         // const url = `http://localhost:8085/time_table_a/course/${data.courseId}/section/A`;
         const url = `http://localhost:8085/time_table_a/course/1/section/A`;
         const response = await axios.get(url, {
            headers: {
               'Authorization': `Bearer ${cookies.token}`
            }
         })

         // console.log(response.data);

         const newFacultyData = {};
         response.data.forEach(x => {
            newFacultyData[x.subjectName] = x.facultyName;
         });
         setData(newFacultyData);
         // console.log(facultylist)
      } catch (error) {
         console.log(error)
      }
   }


   useEffect(() => {
      const fetchData = async () => {
         try {
            await getFacultyList();
         } catch (error) {
            console.log(error)
         }
      };

      fetchData();
   }, []);
   return (
      <div className='course-faculty-list-a'>
         {Object.entries(facultyData).map(([subject, professor], index) => (
            <div key={index} style={{ width: '100%', height: '47px', display: 'flex', alignItems: 'center' }}>
               <div style={{ background: "black", borderRadius: '5rem', height: '40px', width: '40px', marginLeft: '4px' }}></div>
               <div className='cors-fact-name'>{professor}<br /><span style={{ color: '#0094FF' }}>Sub: </span> {subject}</div>
            </div>
         ))}
      </div>
   )
}

const TimeTable = ({ data }) => {

   const [cookies, setCookie] = useCookies(['token', 'student']);
   let days = {
      Monday: {},
      Tuesday: {},
      Wednesday: {},
      Thursday: {},
      Friday: {},
      Saturday: {},
      Sunday: {},
   };

   const [timeTable, setTimeTable] = useState(null);


   const getTimeTable = async () => {

      try {
         // console.log("time table api called")
         // console.log(data.courseId)
         // const url = `http://localhost:8085/time_table_a/course/${data.courseId}/section/A`;
         const url = `http://localhost:8085/time_table_a/course/1/section/A`;
         const response = await axios.get(url, {
            headers: {
               'Authorization': `Bearer ${cookies.token}`
            }
         })

         // console.log(response.data);

         response.data.forEach(x => {
            if (x.day == 'Monday') {
               days.Monday[x.startTime] = x.subjectName;
            }
            if (x.day == 'Tuesday') {
               days.Tuesday[x.startTime] = x.subjectName;
            }
            if (x.day == 'Wednesday') {
               days.Wednesday[x.startTime] = x.subjectName;
            }
            if (x.day == 'Thursday') {
               days.Thursday[x.startTime] = x.subjectName;
            }
            if (x.day == 'Friday') {
               days.Friday[x.startTime] = x.subjectName;
            }
            if (x.day == 'Saturday') {
               days.Saturday[x.startTime] = x.subjectName;
            }
            if (x.day == 'Sunday') {
               days.Sunday[x.startTime] = x.subjectName;
            }
         })

         // console.log(days);
         setTimeTable(days);
         // console.log(timeTable)
      } catch (error) {
         console.log(error)
      }
   }


   useEffect(() => {
      const fetchData = async () => {
         try {
            await getTimeTable();
         } catch (error) {
            console.log(error)
         }
      };

      fetchData();
   }, []);

   return (<table >
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
            <td>{timeTable && timeTable.Monday && timeTable.Monday[900]}</td>
            <td>{timeTable && timeTable.Monday && timeTable.Monday[1000]}</td>
            <td>{timeTable && timeTable.Monday && timeTable.Monday[1100]}</td>
            <td>{timeTable && timeTable.Monday && timeTable.Monday[1200]}</td>
            <td>{timeTable && timeTable.Monday && timeTable.Monday[1300]}</td>
            <td>{timeTable && timeTable.Monday && timeTable.Monday[1400]}</td>
            <td>{timeTable && timeTable.Monday && timeTable.Monday[1500]}</td>


         </tr>
         <tr>
            <td>Tuesday</td>
            <td>{timeTable && timeTable.Tuesday && timeTable.Tuesday[900]}</td>
            <td>{timeTable && timeTable.Tuesday && timeTable.Tuesday[1000]}</td>
            <td>{timeTable && timeTable.Tuesday && timeTable.Tuesday[1100]}</td>
            <td>{timeTable && timeTable.Tuesday && timeTable.Tuesday[1200]}</td>
            <td>{timeTable && timeTable.Tuesday && timeTable.Tuesday[1300]}</td>
            <td>{timeTable && timeTable.Tuesday && timeTable.Tuesday[1400]}</td>
            <td>{timeTable && timeTable.Tuesday && timeTable.Tuesday[1500]}</td>
         </tr>
         <tr>
            <td>Wednesday</td>
            <td>{timeTable && timeTable.Wednesday && timeTable.Wednesday[900]}</td>
            <td>{timeTable && timeTable.Wednesday && timeTable.Wednesday[1000]}</td>
            <td>{timeTable && timeTable.Wednesday && timeTable.Wednesday[1100]}</td>
            <td>{timeTable && timeTable.Wednesday && timeTable.Wednesday[1200]}</td>
            <td>{timeTable && timeTable.Wednesday && timeTable.Wednesday[1300]}</td>
            <td>{timeTable && timeTable.Wednesday && timeTable.Wednesday[1400]}</td>
            <td>{timeTable && timeTable.Wednesday && timeTable.Wednesday[1500]}</td>
         </tr>
         <tr>
            <td>Thursday</td>
            <td>{timeTable && timeTable.Thursday && timeTable.Thursday[900]}</td>
            <td>{timeTable && timeTable.Thursday && timeTable.Thursday[1000]}</td>
            <td>{timeTable && timeTable.Thursday && timeTable.Thursday[1100]}</td>
            <td>{timeTable && timeTable.Thursday && timeTable.Thursday[1200]}</td>
            <td>{timeTable && timeTable.Thursday && timeTable.Thursday[1300]}</td>
            <td>{timeTable && timeTable.Thursday && timeTable.Thursday[1400]}</td>
            <td>{timeTable && timeTable.Thursday && timeTable.Thursday[1500]}</td>
         </tr>
         <tr>
            <td>Friday</td>
            <td>{timeTable && timeTable.Friday && timeTable.Friday[900]}</td>
            <td>{timeTable && timeTable.Friday && timeTable.Friday[1000]}</td>
            <td>{timeTable && timeTable.Friday && timeTable.Friday[1100]}</td>
            <td>{timeTable && timeTable.Friday && timeTable.Friday[1200]}</td>
            <td>{timeTable && timeTable.Friday && timeTable.Friday[1300]}</td>
            <td>{timeTable && timeTable.Friday && timeTable.Friday[1400]}</td>
            <td>{timeTable && timeTable.Friday && timeTable.Friday[1500]}</td>
         </tr>
         <tr>
            <td>Saturday</td>
            <td>{timeTable && timeTable.Saturday && timeTable.Saturday[900]}</td>
            <td>{timeTable && timeTable.Saturday && timeTable.Saturday[1000]}</td>
            <td>{timeTable && timeTable.Saturday && timeTable.Saturday[1100]}</td>
            <td>{timeTable && timeTable.Saturday && timeTable.Saturday[1200]}</td>
            <td>{timeTable && timeTable.Saturday && timeTable.Saturday[1300]}</td>
            <td>{timeTable && timeTable.Saturday && timeTable.Saturday[1400]}</td>
            <td>{timeTable && timeTable.Saturday && timeTable.Saturday[1500]}</td>
         </tr>

         <tr>
            <td style={{ borderRadius: '0 0 0 10px' }}>Sunday</td>
            <td>{timeTable && timeTable.Sunday && timeTable.Sunday[900]}</td>
            <td>{timeTable && timeTable.Sunday && timeTable.Sunday[1000]}</td>
            <td>{timeTable && timeTable.Sunday && timeTable.Sunday[1100]}</td>
            <td>{timeTable && timeTable.Sunday && timeTable.Sunday[1200]}</td>
            <td>{timeTable && timeTable.Sunday && timeTable.Sunday[1300]}</td>
            <td>{timeTable && timeTable.Sunday && timeTable.Sunday[1400]}</td>
            <td style={{ borderRadius: '0 0 10px 0' }}>{timeTable && timeTable.Sunday && timeTable.Sunday[1500]}</td>
         </tr>

      </tbody>
   </table>
   )
}

export default Course




