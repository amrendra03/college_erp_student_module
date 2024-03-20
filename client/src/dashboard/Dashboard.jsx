import axios from 'axios';
import React, { useEffect, useRef, useState } from 'react';
import { useCookies } from 'react-cookie';
import Doughnut from '../component/chart/Doughnut';
import Header from '../component/header/Header';
import CalendarC from '../component/other/Calendar';
import LoadingComponent from '../component/other/LoadingComponent';
import { API } from '../config/env';
import './dashboard.css';

const Dashboard = () => {
   const [cookies, setCookie] = useCookies(['token', 'student']);
   const [student, setStudent] = useState({});
   const [status, setStatus] = useState([]);
   const [loading, setLoading] = useState(true);  // Added loading state
   const [registerdcourse, setRegisteredcourse] = useState({});
   const [courseData, setcoursedata] = useState({})
   const [semesterprogress, setSemesterProgress] = useState({});
   const [fee, setFee] = useState({});

   const [count, setCount] = useState([])

   const statusCall = async (data) => {
      // console.log("Status:")
      // console.log(data)
      if (data.rollNo) {
         // console.log(data.rollNo)
         try {
            const response = await axios.get(`${API.status}/${data.rollNo}`, {
               withCredentials: true,
               headers: {
                  'Authorization': `Bearer ${cookies.token}`
               }
            });
            setStatus(response.data);

            setCount(response.data)

         } catch (error) {
            console.log(error);
         }
      }
      setLoading(false);  // Set loading to false after API call
      // console.log("Status exit.")
   };
   const studentDetail = async () => {
      // e.preventDefault();
      try {
         const response = await axios.get(API.studentDetail, {
            headers: {
               'Authorization': `Bearer ${cookies.token}`
            }
         });
         setStudent(response.data);
         setCookie('student', response.data, { path: '/' });
         statusCall(response.data);
         semesterProgress(response.data);
         getFeeStatus(response.data);
         // getNotification(page)
         // getNotification();
      } catch (error) {
         console.log(error);
      }
      setLoading(false);  // Set loading to false after API call
   };

   const registerdCourse = async () => {
      // e.preventDefault();

      try {

         const token = document.cookie.replace(/(?:(?:^|.*;\s*)token\s*\=\s*([^;]*).*$)|^.*$/, "$1");
         // console.log(token)
         // Set the token in the headers
         axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;


         // console.log("get the registration course list")
         const response = await axios.get(`http://localhost:8085/student/course/registration/RN10000`);
         // console.log(response.data);

         const sortedData = sortDataByTypePriority(response.data);
         // console.log(sortedData[0]);

         setRegisteredcourse(response.data[0]);

         // console.log("get data of a course")
         const response2 = await axios.get(`http://localhost:8085/student/course/${sortedData[0].studentCourseDetailId}`)
         // console.log(response2.data);
         setcoursedata(response2.data);

      } catch (error) {
         console.error('Error fetching data:', error);
      }
   };

   const sortDataByTypePriority = (data) => {
      return data.sort((a, b) => {
         const typePriority = { "PG": 1, "UG": 2, "DP": 3 };
         return typePriority[a.type] - typePriority[b.type];
      });
   };

   const semesterProgress = async (student) => {

      // console.log("semester progress call api. rollNo: " + student.rollNo);
      try {
         const apiUrl = `http://localhost:8085/student/courses/${student.rollNo}/semesters`;
         // Make a POST request
         const response = await axios.get(apiUrl, {
            headers: {
               'Authorization': `Bearer ${cookies.token}`
            }
         });

         // Handle the response
         // console.log('Response:', response.data);

         const inProgressArray = response.data.filter(item => item.status === "In Progress");
         setSemesterProgress(inProgressArray[0]);

         // console.log("Filtered Array with 'In Progress' status:", inProgressArray);

      } catch (error) {
         // Handle errors
         console.error('Error:', error);
      }
   };

   const getFeeStatus = async (student) => {
      // console.log("from fee status api call..")
      try {
         const url = `http://localhost:8085/student/fee-records/${student.rollNo}`;
         const response = await axios.get(url, {
            headers: {
               "Authorization": `Bearer ${cookies.token}`
            }
         })

         // console.log(response);

         response.data.forEach(x => {
            if (x.status != "PAID") {
               setFee(x);
               // console.log(x)
            }
         })
         // setFee(response.data);
         // console.log(fee);
      } catch (error) {
         console.log(error);

      }
   }
   console.log("check useEffect")

   const test = (page) => {
      console.log(page);
   }

   useEffect(() => {
      const fetchData = async () => {
         try {
            await studentDetail();
            await statusCall(student);
            await registerdCourse();
            // await getNotification(page);
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
                  <span className='txt'>{courseData.courseName}</span><br />
                  <span className='txt'>{semesterprogress.name}</span>
               </div>
            </div>
            <div className="Status3 display" >
               <div className='st-icon-3' />
               <div>
                  <span className='txt'>Fee</span><br />
                  <span className='txt' style={{ color: "red" }}>{fee ? fee.feeAmount : 0}</span>
               </div>
            </div>
            <div className="Status4 display" >
               <div className='st-icon-4' />
               <div>
                  <span className='txt'>Fine</span><br />
                  <span className='txt' style={{ color: "red" }}>{fee ? fee.fineAmount : 0}</span>
               </div></div>
            <div className="Status5 display" >
               <div className='st-icon-5' />
               <div>
                  <span className='txt'>Comptetive Score</span><br />
                  <span className='txt' style={{ color: "green" }}>{student.score}</span>
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
                  {/* <div className='notifi-3'  >
                     {notifi.map((item, index) => (
                        <Notifi key={index} data={item} />
                     ))}
                  </div> */}
                  <Notifi />
               </div>
            </div>
            <div className='ds-4'>

               <div className='ds-hex'>

                  <Doughnut data={count} style={{ borderRadius: 10 }} />
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

const NotifiA = () => {

   let page = 0;
   var countNotifi = 5;
   const notifiData = [
      // { message: "Final exam date", active: 0 },
      // { message: "Project submission deadline", active: 0 }
   ];

   const [notifi, setNotifi] = useState(notifiData)

   const getNotification = async () => {

      try {
         const token = document.cookie.replace(/(?:(?:^|.*;\s*)token\s*\=\s*([^;]*).*$)|^.*$/, "$1");
         // console.log(token)
         // Set the token in the headers
         axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
         const url = `http://localhost:8085/notification/?page=${page}`;
         const response = await axios.get(url);

         // console.log(response.data[0])
         response.data.forEach((x) => {
            if (countNotifi > 0) {
               x["active"] = 0
               countNotifi -= 1;
            } else {
               x["active"] = 1

            }
            notifiData.push(x)
         });
         setNotifi(notifiData);
         console.log(notifi)
      } catch (error) {
         console.log(error);
      }
   }

   useEffect(() => {
      getNotification();
   }, [page])

   // getNotification(page);


   return (
      <>
         <div className='notifi-3'  >
            {notifi.map((data, index) => (
               <div className={`notifi-row ${data.active === 1 ? 'notifi-row-color' : ''}`}>
                  <div className={`notifi-icon ${data.active === 1 ? 'notifi-icon-img-d' : 'notifi-icon-img-h'}`} />
                  <div className={`notifi-text ${data.active === 1 ? 'notifi-row-mess' : ''}`}>{data.message}</div>
               </div>
            ))}
         </div>
      </>
   )

}

const Notifi = () => {
   const scrollContainerRef = useRef();
   const [currPage, setCurrPage] = useState(0);
   const [notifi, setNotifi] = useState([]);
   var count = 5;

   const fetchNotification = async () => {
      const url = `http://localhost:8085/notification/?page=${currPage}`;
      try {
         const token = document.cookie.replace(/(?:(?:^|.*;\s*)token\s*\=\s*([^;]*).*$)|^.*$/, "$1");
         axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
         const response = await axios.get(url);
         // console.log(response.data);
         // response.data ? setCurrPage(currPage - 1) : 0;
         setNotifi((prevNotifi) => [...prevNotifi, ...response.data]);
      } catch (error) {
         console.log(error);
      }
   };

   const handleScroll = () => {
      // console.log("scrollA ")
      const { scrollTop, scrollHeight, clientHeight } = scrollContainerRef.current;
      const bottomReached = Math.abs(scrollTop + clientHeight - scrollHeight) < 1;

      if (bottomReached) {
         // User has scrolled to the bottom
         // console.log("scroll C");
         setCurrPage(currPage + 1);
      }
   };

   useEffect(() => {
      fetchNotification();
   }, [currPage])

   return (
      <div className='notifi-3' ref={scrollContainerRef} onScroll={handleScroll} style={{ height: "150px" }}>
         {notifi.map((data, index) => (
            <div key={index} className={`notifi-row ${data.active ? '' : 'notifi-row-color'}`}>
               <div className={`notifi-icon ${data.active ? 'notifi-icon-img-h' : 'notifi-icon-img-d'}`} />
               <div className={`notifi-text ${data.active === 1 ? '' : 'notifi-row-mess'}`}>{data.message}</div>
               {/* {count -= 1} */}
            </div>
         ))}
      </div>
   );
};
export default Dashboard
