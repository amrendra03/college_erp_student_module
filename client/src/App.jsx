import { Route, Routes } from 'react-router';
import './App.css';
import Nav from './component/nav/Nav';
import Dashboard from './dashboard/Dashboard';
import './global/global.css';

function App() {
  return (
    <div className='app'>
      <Nav />
      <Routes>
        <Route path='/' element={<Dashboard />} />
      </Routes>

    </div>
  )
}

export default App
