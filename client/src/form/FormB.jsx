import React, { useState } from 'react';

import { CourseForm, ListCourses, ListStatus, ListStudents, StatusForm, StudentForm } from './Form';
import { dummyCourses, dummyStatuses, dummyStudents } from './data';


const App = () => {
   const [activeComponent, setActiveComponent] = useState('course'); // Default to 'course'

   const switchComponent = (component) => {
      setActiveComponent(component);
   };

   return (
      <div className='form-0'>
         <div className='form-1'>

            <button className="button" onClick={() => switchComponent('course')}>
               Course Form
            </button>
            <button className="button" onClick={() => switchComponent('status')}>
               Status Form
            </button>
            <button className="button" onClick={() => switchComponent('student')}>
               Student Form
            </button>

            {activeComponent === 'course' && <CourseForm />}
            {activeComponent === 'status' && <StatusForm />}
            {activeComponent === 'student' && <StudentForm />}
         </div>
         <div className='form-2'>
            <ListCourses courses={dummyCourses} onDelete={(id) => console.log(`Delete course with ID: ${id}`)} />
            <ListStatus statuses={dummyStatuses} onDelete={(id) => console.log(`Delete status with ID: ${id}`)} />
            <ListStudents students={dummyStudents} onDelete={(rollNo) => console.log(`Delete student with Roll No: ${rollNo}`)} />
         </div>
      </div>
   );
};

export default App;
