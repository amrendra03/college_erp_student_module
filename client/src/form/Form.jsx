import React from 'react';
import { dummyCourses, dummyStatuses, dummyStudents } from './data';
import './form.css';

import { useState } from 'react';

// CourseDto Component
const CourseForm = () => {
   const [courseData, setCourseData] = useState({
      courseName: '',
      batch: '',
      duration: '',
   });

   const submitCourse = () => {
      // Implement API interaction for CourseDto
      console.log('Course submitted', courseData);
      // You can make an API call here with the courseData
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

         <label htmlFor="batch">Batch:</label>
         <input
            type="text"
            id="batch"
            name="batch"
            value={courseData.batch}
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

   const submitStudent = () => {
      const submitStudent = async () => {
         try {
            // Make an API call using Axios
            const response = await axios.post('http://localhost:8080/student', studentData);

            // Log the response from the server
            console.log('API Response:', response.data);

            // You can reset the form or perform any other actions after a successful submission
         } catch (error) {
            // Handle errors
            console.error('Error submitting student data:', error.message);
         }
      };
   };

   const handleChange = (e) => {
      setStudentData({ ...studentData, [e.target.name]: e.target.value });
   };

   return (
      <form className="form-container">
         <h2>Student Information</h2>
         <label htmlFor="rollNo">Roll No:</label>
         <input
            type="text"
            id="rollNo"
            name="rollNo"
            value={studentData.rollNo}
            onChange={handleChange}
            required
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
            Submit
         </button>
      </form>
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
const ListStudents = ({ students, onDelete }) => {
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



// Main App Component
const Form = () => {
   return (
      <div className='form-1'>
         <CourseForm />
         <StatusForm />
         <StudentForm />
         <ListCourses courses={dummyCourses} onDelete={(id) => console.log(`Delete course with ID: ${id}`)} />
         <ListStatus statuses={dummyStatuses} onDelete={(id) => console.log(`Delete status with ID: ${id}`)} />
         <ListStudents students={dummyStudents} onDelete={(rollNo) => console.log(`Delete student with Roll No: ${rollNo}`)} />

      </div>
   );
};

export { CourseForm, ListCourses, ListStatus, ListStudents, StatusForm, StudentForm };
export default Form;
