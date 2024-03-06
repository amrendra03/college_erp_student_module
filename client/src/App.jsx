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
// import Logout from './auth/Logout';


function App() {
  const [cookies] = useCookies(['token']);
  const [authenticated, setAuthenticated] = useState(false);
  const [redirectedFrom, setRedirectedFrom] = useState(null);
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const isAuthenticatedUser = !!cookies.token;

    if (!isAuthenticatedUser) {
      setRedirectedFrom(location.pathname);
      navigate('/login'); // Redirect to login if not authenticated
    }

    setAuthenticated(isAuthenticatedUser);
  }, [cookies.token, location.pathname]);

  const handleLogin = () => {
    setAuthenticated(true);
    navigate(redirectedFrom || '/'); // Redirect to the original route after login
  };

  const handleLogout = () => {
    // console.log("Logout procede")
    setAuthenticated(false);
    document.cookie = 'token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
    cookies.token = "";

    document.cookie.split(";").forEach((c) => {
      document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/");
    });

    navigate('/login')
  };

  useEffect(() => {
    // console.log("authentication update: " + authenticated)

  }, [authenticated])

  return (
    <div className={authenticated ? 'app' : 'app2'}>
      {authenticated && <Nav authenticated={authenticated} onLogout={handleLogout} />}
      <Routes>
        <Route
          path="/login"
          element={authenticated ? <Navigate to={redirectedFrom || '/'} /> : <Login onLogin={handleLogin} setRedirectedFrom={setRedirectedFrom} />}
        />
        <Route path='/' element={authenticated ? <Dashboard /> : <Navigate to='/login' />} />
        <Route path='/course' element={authenticated ? <Course /> : <Navigate to='/login' />} />
        <Route path='/exam' element={authenticated ? <Exam /> : <Navigate to='/login' />} />
        <Route path='/faculty' element={authenticated ? <Faculty /> : <Navigate to='/login' />} />
        <Route path='/payment' element={authenticated ? <Payment /> : <Navigate to='/login' />} />
        <Route path='/setting' element={authenticated ? <Setting /> : <Navigate to='/login' />} />
        <Route path='/logout' element={authenticated ? <></> : <Navigate to='/login' />} />
      </Routes>
    </div>
  );
}

export default App
