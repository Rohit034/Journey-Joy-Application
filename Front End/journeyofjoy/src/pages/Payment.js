import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import UserService from "../service/UserService";

function Payment() {
  const [paymentMethod, setPaymentMethod] = useState('Credit Card');
  const [amount, setAmount] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  // Get tour data from localStorage
  const tourData = JSON.parse(localStorage.getItem('tourData'));
  const tourId=localStorage.getItem('tourId')
 
  useEffect(() => {
    if (tourData) {
      setAmount(tourData.price); // Initialize amount with tourData.price
    } else {
      setError("No tour data found.");
      navigate("/"); // Redirect to home if no tour data
    }
  }, [tourData, navigate]);

  const handlePayment = async (e) => {
    e.preventDefault();
    

    try {
      // First, create the booking
      const bookingData = {
        BookingDate: new Date().toISOString().split('T')[0],
        PaymentStatus: "PENDING",
        tours: tourId,
      };

      
      const resp=await UserService.makeBooking(bookingData);
      const bookingId=resp.data.id
       const paymentDTO = {
        amount: parseFloat(amount),
        paymentmethod: paymentMethod,
        paymentstatus: 'COMPLETED' // Ensure this matches the enum in the backend
      };
       await UserService.makePayment(paymentDTO, bookingId);
       navigate(`/bookingConfirmation`);
      
    } catch (error) {
      console.error("Error making payment:", error);
      setError("Payment failed. Please try again.");
    }
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center mb-4">Payment</h1>
      <form onSubmit={handlePayment}>
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
        <div className="form-group">
          <label htmlFor="paymentMethod">Payment Method</label>
          <select
            id="paymentMethod"
            className="form-control"
            value={paymentMethod}
            onChange={(e) => setPaymentMethod(e.target.value)}
          >
            <option value="Credit Card">Credit Card</option>
            <option value="PayPal">PayPal</option>
          </select>
        </div>
        <button type="submit" className="btn btn-primary">Pay</button>
        {error && <p className="text-danger mt-3">{error}</p>}
      </form>
    </div>
  );
}

export default Payment;
