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
