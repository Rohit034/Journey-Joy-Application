// import React, { useEffect, useState } from "react";
// import { useLocation, useNavigate } from "react-router-dom";
// import UserService from "../service/UserService";

// function Payment() {
//   const [amount, setAmount] = useState('');
//   const [error, setError] = useState('');
//   const navigate = useNavigate();

//   const tourData = JSON.parse(localStorage.getItem('tourData'));
//   const tourId = JSON.parse(localStorage.getItem('tourId'));

//    console.log(tourId)


//   useEffect(() => {
//     if (tourData) {
//       setAmount(tourData.price);
//     } else {
//       setError("No tour data found.");
//       navigate("/"); 
//     }
//   }, [tourData, navigate]);

//   const handleBooking = async (e) => {
//     e.preventDefault();


//     try {
   
//       const bookingData = {
//         BookingDate: new Date().toISOString().split('T')[0],
//         paymentStatus: "PENDING",
      
//         tourId:parseInt(tourId), 
//       };
//       console.log(bookingData)
//      await UserService.makeBooking(bookingData)
//      .then((result)=>{
//       console.log(result)
//       const bookingId=result.data.id
//       localStorage.setItem('bookingId',bookingId)
//      })
 
//       navigate('/makePayment');

//     } catch (error) {
//       console.error("Error making booking:", error);
//       if (error.response) {
       
      
       
//         setError(`Server Error: ${error.response.data.message || 'Booking failed. Please try again.'}`);
//       } else if (error.request) {
      
//         setError('Network Error: Please check your connection.');
//       } else {
      
//         setError(`Error: ${error.message}`);
//       }
//     }
//   };

//   return (
//     <div className="container mt-5">
//       <h1 className="text-center mb-4">Booking</h1>
//       <form onSubmit={handleBooking}>
//         <div className="form-group">
//           <label htmlFor="amount">Amount</label>
//           <input
//             type="text"
//             className="form-control"
//             id="amount"
//             value={amount}
//             readOnly
//           />
//         </div>
//         <button type="submit" className="btn btn-primary">Create Booking</button>
//         {error && <p className="text-danger mt-3">{error}</p>}
//       </form>
//     </div>
//   );
// }

// export default Payment;

import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { motion } from "framer-motion";
import UserService from "../service/UserService";
import Image from "../images/image2.webp"; // Ensure this path is correct

function Payment() {
  const [amount, setAmount] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const tourData = JSON.parse(localStorage.getItem("tourData"));
  const tourId = JSON.parse(localStorage.getItem("tourId"));

  useEffect(() => {
    if (tourData) {
      setAmount(tourData.price);
    } else {
      setError("No tour data found.");
      navigate("/");
    }
  }, [tourData, navigate]);

  const handleBooking = async (e) => {
    e.preventDefault();

    try {
      const bookingData = {
        BookingDate: new Date().toISOString().split("T")[0],
        paymentStatus: "PENDING",
        tourId: parseInt(tourId),
      };
      console.log(bookingData);
      const result = await UserService.makeBooking(bookingData);
      const bookingId = result.data.id;
      localStorage.setItem("bookingId", bookingId);

      navigate("/makePayment");
    } catch (error) {
      console.error("Error making booking:", error);
      if (error.response) {
        setError(
          `Server Error: ${error.response.data.message || "Booking failed. Please try again."}`
        );
      } else if (error.request) {
        setError("Network Error: Please check your connection.");
      } else {
        setError(`Error: ${error.message}`);
      }
    }
  };

  const styles = {
    bgGradient: {
      background: "linear-gradient(to bottom right, #4c0099, #3c0078)",
    },
    bgGradientDark: {
      background: "linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.2))",
    },
  };

  return (
    <div
      style={{ ...styles.bgGradient, overflow: "hidden", position: "relative", minHeight: "100vh" }}
      className="bg-gradient"
    >
      {/* Background Image */}
      <motion.div
        initial={{ scale: 1.1 }}
        animate={{ scale: 1 }}
        transition={{ duration: 20, repeat: Infinity, repeatType: "reverse" }}
        className="position-absolute w-100 h-100"
        style={{ top: 0, left: 0 }}
      >
        <img
          src={Image}
          alt="Scenic mountain aurora"
          className="w-100 h-100 object-cover"
          style={{ mixBlendMode: "overlay", opacity: 0.6 }}
        />
        <div
          className="position-absolute top-0 start-0 w-100 h-100"
          style={styles.bgGradientDark}
        ></div>
      </motion.div>

      {/* Main Content */}
      <div
        className="container position-relative text-white d-flex align-items-center justify-content-center"
        style={{ zIndex: 10, minHeight: "100vh" }}
      >
        <div className="bg-white p-5 rounded shadow text-dark w-100" style={{ maxWidth: "500px" }}>
          <h1 className="text-center mb-4">Booking</h1>
          <form onSubmit={handleBooking}>
            <div className="form-group">
              <label htmlFor="amount">Amount</label>
              <input
                type="text"
                className="form-control"
                id="amount"
                value={amount}
                readOnly
              />
            </div>
            <button type="submit" className="btn btn-primary w-100 mt-4">Create Booking</button>
            {error && <p className="text-danger mt-3">{error}</p>}
          </form>
        </div>
      </div>
    </div>
  );
}

export default Payment;
