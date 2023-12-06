// Dummy data for ListCourses component
const dummyCourses = [
   { id: 1, courseName: 'Computer Science', batch: '2022', duration: '4 years' },
   { id: 2, courseName: 'Electrical Engineering', batch: '2023', duration: '5 years' },
   { id: 3, courseName: 'Business Administration', batch: '2021', duration: '3 years' },
];

// Dummy data for ListStatus component
const dummyStatuses = [
   {
      id: 1,
      studentRollNo: '123',
      subject: 'Mathematics',
      subjectCode: 'MATH101',
      facultyName: 'Dr. Smith',
      present: 15,
      absent: 3,
      submitted: 10,
      notSubmitted: 8,
   },
   {
      id: 2,
      studentRollNo: '456',
      subject: 'Physics',
      subjectCode: 'PHYS102',
      facultyName: 'Prof. Johnson',
      present: 12,
      absent: 5,
      submitted: 8,
      notSubmitted: 9,
   },
   {
      id: 3,
      studentRollNo: '789',
      subject: 'Computer Science',
      subjectCode: 'CS101',
      facultyName: 'Dr. Williams',
      present: 18,
      absent: 1,
      submitted: 12,
      notSubmitted: 7,
   },
];

// Dummy data for ListStudents component
const dummyStudents = [
   { rollNo: '101', name: 'John Doe', course: 'Computer Science', sem: 2, fee: 2000, fine: 50, score: 85 },
   { rollNo: '202', name: 'Jane Smith', course: 'Electrical Engineering', sem: 3, fee: 2500, fine: 20, score: 78 },
   { rollNo: '303', name: 'Bob Johnson', course: 'Business Administration', sem: 1, fee: 1800, fine: 10, score: 92 },
];

export { dummyCourses, dummyStatuses, dummyStudents };
