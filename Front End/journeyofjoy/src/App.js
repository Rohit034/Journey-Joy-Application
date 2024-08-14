import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import MainNavBar from './Components/MainNavBar';
import { Route, Routes } from 'react-router-dom';
import SignIn from './pages/SignIn';
import Header from './Components/Header';
import TourList from './pages/TourList';
import SignUp from './pages/SignUp';

function App() {
  return (
    <div>
      <Header></Header>
      <MainNavBar></MainNavBar>
      <Routes>
      <Route path="/SignIn" element={<SignIn/>} />
      <Route path="/TourList" element={<TourList/>} />
      <Route path="/SignUp" element={<SignUp></SignUp>}></Route>
      </Routes>
    </div>
  );
}

export default App;
