// import React, { useState, useEffect } from "react";
// import { useNavigate } from "react-router-dom";
// import UserService from "../service/UserService";

// function PaymentPage() {
//   const [amount, setAmount] = useState('');
//   const [bookingId, setBookingId] = useState('');
//   const [error, setError] = useState('');
//   const navigate = useNavigate();

//   useEffect(() => {
//     const id = localStorage.getItem('bookingId');
//     if (id) {
//       setBookingId(id);

//       const storedTourData = JSON.parse(localStorage.getItem('tourData'));
//       if (storedTourData) {
//         setAmount(storedTourData.price);
//       }
//     } else {
//       setError("Booking ID is missing.");
//       navigate("/"); 
//     }
//   }, [navigate]);

//   const handlePayment = async () => {
//     try {
//       // 1. Request to create a payment order from the backend
//       const paymentRequest = {
//         bookingId: bookingId,
//         amount: parseFloat(amount),
//         // customerPhone: "9000090000", // Replace with the actual customer phone
//         // customerEmail: "gaurav.kumar@example.com", // Replace with the actual customer email
//       };

//       const orderId = await UserService.createPaymentOrder(paymentRequest);

//       // 2. Initialize Cashfree SDK
//       const cashfree = window.Cashfree({ mode: "sandbox" }); // Change to "production" in production

//       const checkoutOptions = {
//         paymentSessionId: orderId,
//         redirectTarget: "_modal", // Opens in a pop-up modal
//       };

//       // 3. Open Cashfree Checkout
//       cashfree.checkout(checkoutOptions).then((result) => {
//         if (result.error) {
//           console.log("Payment Error:", result.error);
//           setError("Payment failed. Please try again.");
//         }
//         if (result.paymentDetails) {
//           console.log("Payment Success:", result.paymentDetails.paymentMessage);
//           alert("Payment successful");
//           localStorage.removeItem('bookingId');
//           navigate('/');
//         }
//       });

//     } catch (error) {
//       console.error("Error initiating payment:", error);
//       setError("Payment initialization failed. Please try again.");
//     }
//   };

//   return (
//     <div className="container mt-5">
//       <h1 className="text-center mb-4">Payment</h1>
//       <form>
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
//         <button type="button" className="btn btn-success mt-3" onClick={handlePayment}>
//           Pay Now
//         </button>
//         {error && <p className="text-danger mt-3">{error}</p>}
//       </form>
//     </div>
//   );
// }

// export default PaymentPage;

import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import UserService from "../service/UserService";

function PaymentPage() {
  const [amount, setAmount] = useState('');
  const [bookingId, setBookingId] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const id = localStorage.getItem('bookingId');
    if (id) {
      setBookingId(id);

      const storedTourData = JSON.parse(localStorage.getItem('tourData'));
      if (storedTourData) {
        setAmount(storedTourData.price);
      }
    } else {
      setError("Booking ID is missing.");
      navigate("/"); 
    }
  }, [navigate]);

  const handlePayment = async () => {
    try {
      // Request to create a payment order from the backend
      const paymentRequest = {
        bookingId: bookingId,
        amount: parseFloat(amount),
        // customerPhone: "9000090000", // Replace with the actual customer phone
        // customerEmail: "gaurav.kumar@example.com", // Replace with the actual customer email
      };

      // Ensure backend returns an orderId
      const orderId = await UserService.createPaymentOrder(paymentRequest);

      if (!orderId) {
        setError("Failed to create payment order. Please try again.");
        return;
      }

      // Initialize Cashfree SDK
      const cashfree = window.Cashfree({ mode: "sandbox" }); // Change to "production" in production

      const checkoutOptions = {
        paymentSessionId: orderId,
        redirectTarget: "_modal", // Opens in a pop-up modal
      };

      // Open Cashfree Checkout
      cashfree.checkout(checkoutOptions).then((result) => {
        if (result.error) {
          console.log("Payment Error:", result.error);
          setError("Payment failed. Please try again.");
        }
        if (result.paymentDetails) {
          console.log("Payment Success:", result.paymentDetails.paymentMessage);
          alert("Payment successful");
          localStorage.removeItem('bookingId');
          navigate('/');
        }
      });

    } catch (error) {
      console.error("Error initiating payment:", error);
      setError("Payment initialization failed. Please try again.");
    }
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center mb-4">Payment</h1>
      <form>
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
        <button type="button" className="btn btn-success mt-3" onClick={handlePayment}>
          Pay Now
        </button>
        {error && <p className="text-danger mt-3">{error}</p>}
      </form>
    </div>
  );
}

export default PaymentPage;

