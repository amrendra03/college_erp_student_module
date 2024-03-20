import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useCookies } from 'react-cookie';
import Header from '../component/header/Header';
import LoadingComponent from '../component/other/LoadingComponent';
import ExamForm from './component/ExamForm';
import ExamList from './component/ExamList';
import './exam.css';

const Exam = () => {
   const [cookies, setCookie] = useCookies(['token', 'student']);
   const [loading, setLoading] = useState(true);
   const [studentData, setStudentData] = useState({});
   const [examListData, setExamListData] = useState([]);
   const [examSelected, setExam] = useState(null);

   const studentDataFetching = async () => {
      try {
         // console.log("Api call  Student data... exam")
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
      setLoading(false);
   };
   function formatDateTime(inputDateTime) {
      const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
      const formattedDate = new Date(inputDateTime).toLocaleDateString('en-GB', options);
      return formattedDate;
   }

   const Data = async () => {
      try {
         console.log("Api call  Student data... exam")
         const response = await axios.get(``, {
            headers: {
               'Authorization': `Bearer ${cookies.token}`
            }
         });

         let data = response.data;
         console.log(response.data)
      } catch (error) {
         console.log(error);
      }
      setLoading(false);
   };

   const examList = async () => {
      try {
         // console.log("Api call ... exam List")
         const response = await axios.get(`http://localhost:8085/student/exam/record`, {
            headers: {
               'Authorization': `Bearer ${cookies.token}`
            }
         });

         let data = response.data;
         // console.log(response.data)
         setExamListData(data);
      } catch (error) {
         console.log(error);
      }
      setLoading(false);
   };

   useEffect(() => {
      const fetchData = async () => {
         try {
            await studentDataFetching();
            await examList();
         } catch (error) {
            console.log(error);
         } finally {
            setLoading(false);
         }
      };
      const timer = setTimeout(() => {
         fetchData();
      }, 200);

      return () => clearTimeout(timer);
   }, []);

   if (loading) {
      return <LoadingComponent />;
   }

   const handleExamClick = (data) => {
      console.log("clicked")
      setExam(data);
      // console.log(data)
   };

   return (
      <div className='exam'>
         <Header data={"Exam"} />
         <div className='exam-panel'>
            <div className='ep-list'>
               <p className='p'>Exam List</p>
               <div className='exam-list-container'>


                  {examListData.map((data, index) => (
                     <div key={index} className='exam-list-item active-exam' onClick={() => handleExamClick(data)}>
                        <ExamList data={data} />
                     </div>
                  ))}


               </div>
            </div>
            <div className='ep-form'>
               {
                  // console.log()
                  examSelected != null ? <ExamForm data={examSelected} /> : <div style={{ background: 'white' }} className='ex-f-n' />
               }
            </div>
         </div>
      </div>
   )
}


// ExamList
// ExamForm
// ExamProcess
// ExamStatus

// --------------- AA
// --------------- BB
// --------------- CC
// --------------- DD
// --------------- EE
// --------------- FF
// --------------- GG

export default Exam
