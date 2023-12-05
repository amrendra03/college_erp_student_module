import { Route, Routes } from 'react-router';
import './App.css';
import Nav from './component/nav/Nav';
import Course from './course/Course';
import Dashboard from './dashboard/Dashboard';
import Exam from './exam/Exam';
import Faculty from './faculty/Faculty';
import './global/global.css';
import Payment from './payment/Payment';

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
      </Routes>

    </div>
  )
}

export default App
