import { Navigate, Route, Routes, useLocation, useNavigate } from 'react-router';
import './App.css';
// import Form from './form/Form';
import { useEffect, useState } from 'react';
import './global/global.css';

import { useCookies } from 'react-cookie';
import Login from './auth/Login';
import Nav from './component/nav/Nav';
import Course from './course/Course';
import Dashboard from './dashboard/Dashboard';
import Exam from './exam/Exam';
import Faculty from './faculty/Faculty';
import Payment from './payment/Payment';
import Setting from './setting/Setting';


function App() {

  const [cookies] = useCookies(['token']);
  const [authenticated, setAuthenticated] = useState(false);
  const [redirectedFrom, setRedirectedFrom] = useState(null); // To store the redirected route
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const isAuthenticatedUser = !!cookies.token;

    // console.log(cookies.token);

    // If not authenticated, store the current route in redirectedFrom state
    if (!isAuthenticatedUser) {
      setRedirectedFrom(location.pathname);
    }

    setAuthenticated(isAuthenticatedUser);
  }, [cookies.token, location.pathname]);


  const handleLogin = () => {
    setAuthenticated(true);
  };
  const handleLogout = () => {
    // Clear the token from cookies on logout
    document.cookie = 'token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
    setAuthenticated(false);
  };


  return (
    <div className={authenticated ? 'app' : 'app2'}>
      {console.log(authenticated)}
      {authenticated ? <Nav authenticated={authenticated} onLogout={handleLogout} /> : <></>}
      <Routes>
        <Route
          path="/login"
          element={authenticated ? (
            <Navigate to={redirectedFrom || '/'} />
          ) : (

            <Login onLogin={handleLogin} setRedirectedFrom={setRedirectedFrom} />
          )} />

        <Route path='/' element={authenticated ? <Dashboard /> : <Navigate to='/login' />} />
        <Route path='/course' element={authenticated ? <Course /> : <Navigate to='/login' />} />
        <Route path='/exam' element={authenticated ? <Exam /> : <Navigate to='/login' />} />
        <Route path='/faculty' element={authenticated ? <Faculty /> : <Navigate to='/login' />} />
        <Route path='/payment' element={authenticated ? <Payment /> : <Navigate to='/login' />} />
        <Route path='/setting' element={authenticated ? <Setting /> : <Navigate to='/login' />} />
        <Route path='/logout' element={authenticated ? <></> : <Navigate to='/login' />} />

        {/* <Route path='*' element={<Navigate to='/login' />} /> */}
      </Routes>

    </div>
  );
}

export default App
