import { Route, Routes } from 'react-router';
import './App.css';
import Nav from './component/nav/Nav';
import Course from './course/Course';
import Dashboard from './dashboard/Dashboard';
import Exam from './exam/Exam';
import Faculty from './faculty/Faculty';
// import Form from './form/Form';
import FormB from './form/FormB';
import './global/global.css';
import Payment from './payment/Payment';
import Setting from './setting/Setting';

function App() {
  return (
    <div className='app'>
      <Nav />
      <Routes>
        <Route path='/' element={<Dashboard />} />
        <Route path='/course' element={<Course />} />
        <Route path="/exam" element={<Exam />} />
        <Route path="/faculty" element={<Faculty />} />
        <Route path="/payment" element={<Payment />} />
        <Route path='/form' element={<FormB />} />
        <Route path='/setting' element={<Setting />} />
      </Routes>

    </div>
  )
}

export default App

// function App() {
//   const [authenticated, setAuthenticated] = useState(false);

//   useEffect(() => {
//     // Check for the presence of a token (modify this logic based on your authentication mechanism)
//     // const token = localStorage.getItem('token');
//     const isAuthenticatedUser = !!token;

//     setAuthenticated(isAuthenticatedUser);
//   }, []);

//   return (
//     <div className='app'>
//       {authenticated && <Nav />}
//       <Routes>
//         <Route path='/' element={<Dashboard />} />
//         <Route path='/login' element={<Login />} />
//         <AuthenticatedRoute path='/course' element={<Course />} />
//         <AuthenticatedRoute path="/exam" element={<Exam />} />
//         <AuthenticatedRoute path="/faculty" element={<Faculty />} />
//         <AuthenticatedRoute path="/payment" element={<Payment />} />
//         <AuthenticatedRoute path='/form' element={<FormB />} />
//         <AuthenticatedRoute path='/setting' element={<Setting />} />
//         {!authenticated && <Navigate to={API.LOGIN} />} {/* Redirect to login page if not authenticated */}
//       </Routes>
//     </div>
//   );
// }

// export default App;
