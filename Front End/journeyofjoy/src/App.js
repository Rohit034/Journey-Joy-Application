import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import MainNavBar from './Components/MainNavBar';
import { Route, Routes } from 'react-router-dom';
import SignIn from './pages/SignIn';
import Header from './Components/Header';
import TourList from './pages/TourList';
import SignUp from './pages/SignUp';
import Payment from './pages/payment';
import PaymentPage from './pages/PaymentPage';
import Logout from './pages/Logout';
import Footer from './Components/Footer';


function App() {
  return (
    <div>
      <Header></Header>
      <MainNavBar></MainNavBar>
      <Routes>
      <Route path="/" element={<TourList/>} />
      <Route path="/SignIn" element={<SignIn/>} />
      <Route path="/TourList" element={<TourList/>} />
      <Route path="/payment"element={<Payment/>} ></Route>
      <Route path='/makePayment' element={<PaymentPage/>}/>
      <Route path="/SignUp" element={<SignUp></SignUp>}></Route>
      <Route path="/logout" element={<Logout />} />
      </Routes>
      <Footer></Footer>
    </div>
  );
}

export default App;
