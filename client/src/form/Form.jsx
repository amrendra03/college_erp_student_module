import axios from 'axios';
import React, { useEffect } from 'react';
import './form.css';

import { useState } from 'react';
import { API } from '../config/env';



// CourseDto Component
const CourseForm = () => {
   const [courseData, setCourseData] = useState({
      duration: '',
      courseName: '',
      courseBranch: '',
   });

   const submitCourse = async () => {
      // Implement API interaction for CourseDto
      console.log('Course submitted', courseData);
      // You can make an API call here with the courseData
      try {


         const res = await axios.post(`${API.HOST}/student/course/`, courseData)
         console.log(res.data);

      } catch (error) {
         console.log(error)

      }
   };

   const handleChange = (e) => {
      setCourseData({ ...courseData, [e.target.name]: e.target.value });
   };

   return (
      <form className="form-container">
         <h2>Course Information</h2>
         <label htmlFor="courseName">Course Name:</label>
         <input
            type="text"
            id="courseName"
            name="courseName"
            value={courseData.courseName}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="batch">Branch:</label>
         <input
            type="text"
            id="batch"
            name="courseBranch"
            value={courseData.courseBranch}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="duration">Duration:</label>
         <input
            type="text"
            id="duration"
            name="duration"
            value={courseData.duration}
            onChange={handleChange}
            required
            className="input"
         />

         <button type="button" onClick={submitCourse} className="button">
            Submit
         </button>
      </form>
   );
};

// SemesterDto Component
const SemesterForm = () => {
   const [semData, setSemData] = useState({
      courseId: '',
      semesterNumber: '',
      // courseBranch: '',
   });

   const submitCourse = async () => {
      // Implement API interaction for CourseDto
      console.log('Course submitted', semData);
      // You can make an API call here with the courseData
      try {


         const res = await axios.post(`${API.HOST}/student/course/`, semData)
         console.log(res.data);

      } catch (error) {
         console.log(error)

      }
   };

   const handleChange = (e) => {
      setSemData({ ...semData, [e.target.name]: e.target.value });
   };

   return (
      <form className="form-container">
         <h2>Sem Information</h2>
         <label htmlFor="courseName">Course Id:</label>
         <input
            type="number"
            id="courseName"
            name="courseId"
            value={semData.courseId}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="batch">Sem No:</label>
         <input
            type="number"
            id="batch"
            name="semesterNumber"
            value={semData.semesterNumber}
            onChange={handleChange}
            required
            className="input"
         />

         {/* <label htmlFor="duration">Duration:</label>
         <input
            type="text"
            id="duration"
            name="duration"
            value={semData.duration}
            onChange={handleChange}
            required
            className="input"
         /> */}

         <button type="button" onClick={submitCourse} className="button">
            Submit
         </button>
      </form>
   );
};

// StatusDto Component
const StatusForm = () => {
   const [statusData, setStatusData] = useState({
      studentRollNo: '',
      subject: '',
      subjectCode: '',
      facultyName: '',
      present: 0,
      absent: 0,
      submitted: 0,
      notSubmitted: 0,
   });

   const submitStatus = () => {
      // Implement API interaction for StatusDto
      console.log('Status submitted', statusData);
      // You can make an API call here with the statusData
   };

   const handleChange = (e) => {
      setStatusData({ ...statusData, [e.target.name]: e.target.value });
   };

   return (
      <form className="form-container">
         <h2>Status Information</h2>
         <label htmlFor="studentRollNo">Student Roll No:</label>
         <input
            type="text"
            id="studentRollNo"
            name="studentRollNo"
            value={statusData.studentRollNo}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="subject">Subject:</label>
         <input
            type="text"
            id="subject"
            name="subject"
            value={statusData.subject}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="subjectCode">Subject Code:</label>
         <input
            type="text"
            id="subjectCode"
            name="subjectCode"
            value={statusData.subjectCode}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="facultyName">Faculty Name:</label>
         <input
            type="text"
            id="facultyName"
            name="facultyName"
            value={statusData.facultyName}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="present">Present:</label>
         <input
            type="number"
            id="present"
            name="present"
            value={statusData.present}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="absent">Absent:</label>
         <input
            type="number"
            id="absent"
            name="absent"
            value={statusData.absent}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="submitted">Submitted:</label>
         <input
            type="number"
            id="submitted"
            name="submitted"
            value={statusData.submitted}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="notSubmitted">Not Submitted:</label>
         <input
            type="number"
            id="notSubmitted"
            name="notSubmitted"
            value={statusData.notSubmitted}
            onChange={handleChange}
            required
            className="input"
         />

         <button type="button" onClick={submitStatus} className="button">
            Submit
         </button>
      </form>
   );
};

// StudentDto Component
const StudentForm = () => {
   const [studentData, setStudentData] = useState({
      rollNo: '',
      name: '',
      course: '',
      sem: 0,
      fee: 0,
      fine: 0,
      score: 0,
   });


   const submitStudent = async () => {
      console.log("Submit student..")
      try {
         // console.log(studentData);
         // delete studentData.rollNo;
         const { rollNo, ...studentDataWithoutRollNo } = studentData;

         console.log(studentDataWithoutRollNo);
         const response = await axios.post(`${API.HOST}/student/create`, studentDataWithoutRollNo);
         console.log('API Response:', response.data);
      } catch (error) {
         console.error('Error submitting student data:', error.message);
      }
   };

   const updateStudent = async () => {
      console.log("Submit student..")
      console.log(studentData);
      try {
         // console.log(studentData);
         const response = await axios.put(`${API.HOST}/student/update/${studentData.rollNo}`, studentData);
         console.log('API Response:', response.data);
      } catch (error) {
         console.error('Error submitting student data:', error.message);
      }
   };


   const handleChange = (e) => {
      setStudentData({ ...studentData, [e.target.name]: e.target.value });
   };

   return (
      <div className="form-container">
         <h2>Student Information</h2>
         <label htmlFor="rollNo">Roll No:</label>
         <input
            type="text"
            id="rollNo"
            name="rollNo"
            value={studentData.rollNo}
            onChange={handleChange}
            className="input"
         />

         <label htmlFor="name">Name:</label>
         <input
            type="text"
            id="name"
            name="name"
            value={studentData.name}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="course">Course:</label>
         <input
            type="text"
            id="course"
            name="course"
            value={studentData.course}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="sem">Semester:</label>
         <input
            type="number"
            id="sem"
            name="sem"
            value={studentData.sem}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="fee">Fee:</label>
         <input
            type="number"
            id="fee"
            name="fee"
            value={studentData.fee}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="fine">Fine:</label>
         <input
            type="number"
            id="fine"
            name="fine"
            value={studentData.fine}
            onChange={handleChange}
            required
            className="input"
         />

         <label htmlFor="score">Score:</label>
         <input
            type="number"
            id="score"
            name="score"
            value={studentData.score}
            onChange={handleChange}
            required
            className="input"
         />

         <button type="button" onClick={submitStudent} className="button">
            Create
         </button>
         <button type="button" onClick={updateStudent} className="button">
            Update
         </button>
      </div>
   );
};

// ListCourses Component
const ListCourses = ({ courses, onDelete }) => {
   return (
      <div className="list-container">
         <h2>List of Courses</h2>
         {courses.map((course) => (
            <div className="list-item" key={course.id}>
               <div>
                  <span>Course Name:</span> {course.courseName}
               </div>
               <div>
                  <span>Batch:</span> {course.batch}
               </div>
               <div>
                  <span>Duration:</span> {course.duration}
               </div>
               <button className="delete-button" onClick={() => onDelete(course.id)}>
                  Delete
               </button>
            </div>
         ))}
      </div>
   );
};

// ListStatus Component
const ListStatus = ({ statuses, onDelete }) => {
   return (
      <div className="list-container">
         <h2>List of Student Status</h2>
         {statuses.map((status) => (
            <div className="list-item" key={status.id}>
               <div>
                  <span>Roll No:</span> {status.studentRollNo}
               </div>
               <div>
                  <span>Subject:</span> {status.subject}
               </div>
               <div>
                  <span>Faculty:</span> {status.facultyName}
               </div>
               <div>
                  <span>Present:</span> {status.present}
               </div>
               <div>
                  <span>Absent:</span> {status.absent}
               </div>
               <button className="delete-button" onClick={() => onDelete(status.id)}>
                  Delete
               </button>
            </div>
         ))}
      </div>
   );
};

// ListStudents Component
const ListStudents = () => {
   const [students, setStudents] = useState([]);

   const studentList = async () => {
      // console.log("Fetching student data...");
      try {
         const response = await axios.get(`${API.HOST}/student/all?pageNumber=&pageSize=&sortBy=rollNo`);
         // console.log('API Response:', response.data);
         setStudents(response.data.content);
      } catch (error) {
         console.error('Error fetching student data:', error.message);
      }
   }



   // Implement your delete logic here

   const onDelete = async (rollNo) => {
      console.log("-- " + rollNo);
      try {
         const response = await axios.delete(`${API.HOST}/student/delete/${rollNo}`);
         console.log('API Response:', response.data);
         // delete the studetn from memory
         setStudents((prevStudents) => prevStudents.filter((student) => student.rollNo !== rollNo));

      } catch (error) {
         console.error('Error fetching student data:', error.message);
      }
   };

   useEffect(() => {
      studentList();
   }, []); // Use an empty dependency array to run the effect only once on mount


   return (
      <div className="list-container">
         <h2>List of Students</h2>
         {students.map((student) => (

            <div className="list-item" key={student.rollNo}>
               <div>
                  <span>Name:</span> {student.name}
               </div>
               <div>
                  <span>Roll No:</span> {student.rollNo}
               </div>
               <div>
                  <span>Course:</span> {student.course}
               </div>
               <div>
                  <span>Semester:</span> {student.sem}
               </div>
               <div>
                  <span>Fee:</span> ${student.fee}
               </div>
               <div>
                  <span>Fine:</span> ${student.fine}
               </div>
               <div>
                  <span>Score:</span> {student.score}
               </div>
               <button className="delete-button" onClick={() => onDelete(student.rollNo)}>
                  Delete
               </button>
            </div>
         ))}
      </div>
   );
};


const SingleStudentForm = () => {
   const [rollNo, setRollNo] = useState('');
   const [studentInfo, setStudentInfo] = useState(null);
   const [error, setError] = useState('');

   const getStudentInfo = async () => {
      try {
         const response = await axios.get(`${API.HOST}/student/get/${rollNo}`);
         setStudentInfo(response.data);
         setError('');
      } catch (error) {
         setStudentInfo(null);
         setError('Student not found.');
      }
   };

   return (
      <div className="single-student-form">
         <h2>Get Student Information</h2>
         <div className="form-group">
            <label htmlFor="rollNo">Roll No:</label>
            <input
               type="text"
               id="rollNo"
               value={rollNo}
               className="input"
               onChange={(e) => setRollNo(e.target.value)}
            />
         </div>
         <button onClick={getStudentInfo}>Get Student Info</button>
         {error && <p className="error-message">{error}</p>}
         {studentInfo && (
            <div className="student-info">
               <h3 style={{ margin: 0 }}>Student Information</h3>
               <p><strong>Name:</strong> {studentInfo.name}</p>
               <p><strong>Roll No:</strong> {studentInfo.rollNo}</p>
               <p><strong>Course:</strong> {studentInfo.course}</p>
               <p><strong>Sem:</strong> {studentInfo.sem}</p>
               <p><strong>Fee:</strong> {studentInfo.fee}</p>
               <p><strong>Fine:</strong> {studentInfo.fine}</p>
               <p><strong>Score:</strong> {studentInfo.score}</p>
               {/* Add more details as needed */}
            </div>
         )}
      </div>
   );
};



export { CourseForm, ListCourses, ListStatus, ListStudents, SemesterForm, SingleStudentForm, StatusForm, StudentForm };

