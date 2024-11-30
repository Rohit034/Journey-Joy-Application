import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import UserService from "../service/UserService";

function PaymentPage() {
  const [paymentMethod, setPaymentMethod] = useState('Credit Card');
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

  const handlePayment = async (e) => {
    e.preventDefault();

    try {
      const paymentDTO = {
        amount: parseFloat(amount),
        paymentmethod: paymentMethod,
        paymentdate: new Date().toISOString().split('T')[0],
        paymentstatus: 'COMPLETED', 
      };

      await UserService.makePayment(paymentDTO, bookingId);
      alert("Payment is completed");
      localStorage.removeItem('bookingId');
      navigate('/');
    } catch (error) {
      console.error("Error making payment:", error);
      if (error.response) {
        setError(`Server Error: ${error.response.data.message || 'Payment failed. Please try again.'}`);
      } else if (error.request) {
        setError('Network Error: Please check your connection.');
      } else {
        setError(`Error: ${error.message}`);
      }
    }
  };

  const initiateRazorpay = () => {
    const options = {
      key: "YOUR_KEY_ID", // Replace with your Razorpay Key ID
      amount: amount * 100, // Convert to paise (Razorpay uses the smallest currency unit)
      currency: "INR",
      name: "Journey of Joy",
      description: "Tour Payment",
      image: "https://example.com/your_logo", // Optional: your company logo
      order_id: "order_IluGWxBm9U8zJ8", // Replace with the actual order ID you get from your server
      handler: function (response) {
        alert(`Payment successful!\nPayment ID: ${response.razorpay_payment_id}\nOrder ID: ${response.razorpay_order_id}\nSignature: ${response.razorpay_signature}`);
        // Optionally call your backend to confirm payment status
      },
      prefill: {
        name: "Gaurav Kumar",
        email: "gaurav.kumar@example.com",
        contact: "9000090000",
      },
      notes: {
        address: "Razorpay Corporate Office",
      },
      theme: {
        color: "#3399cc", // Customize theme color
      },
    };

    const rzp1 = new Razorpay(options);
    rzp1.on('payment.failed', function (response) {
      alert(`Payment failed! Error: ${response.error.description}`);
    });

    rzp1.open();
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
            <option value="Bank Transfer">Bank Transfer</option>
            <option value="Google Pay">Google Pay</option>
            <option value="Apple Pay">Apple Pay</option>
          </select>
        </div>
        <button type="submit" className="btn btn-primary">Pay</button>
        {error && <p className="text-danger mt-3">{error}</p>}
      </form>

      <button id="rzp-button1" className="btn btn-success mt-3" onClick={initiateRazorpay}>Pay with Razorpay</button>

      {/* Razorpay Checkout Script */}
      <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
    </div>
  );
}

export default PaymentPage;
