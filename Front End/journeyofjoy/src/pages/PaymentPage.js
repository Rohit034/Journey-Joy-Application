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

    // Dynamically load Razorpay script
    const script = document.createElement('script');
    script.src = "https://checkout.razorpay.com/v1/checkout.js";
    script.async = true;
    script.onload = () => {
      console.log("Razorpay script loaded successfully.");
    };
    script.onerror = () => {
      console.error("Error loading Razorpay script.");
    };
    document.body.appendChild(script);

    return () => {
      document.body.removeChild(script); // Clean up when the component is unmounted
    };
  }, [navigate]);

  const handlePayment = async (paymentResponse) => {
    try {
      const paymentDTO = {
        amount: parseFloat(amount),
        paymentmethod: 'Razorpay',
        paymentdate: new Date().toISOString().split('T')[0],
        paymentstatus: 'COMPLETED', 
        razorpay_payment_id: paymentResponse.razorpay_payment_id,
        razorpay_order_id: paymentResponse.razorpay_order_id,
        razorpay_signature: paymentResponse.razorpay_signature
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
    if (window.Razorpay) {
      const options = {
        key: "rzp_test_ZTJhkchgr8wSYR", // Replace with your Razorpay Key ID
        amount: amount * 100, // Convert to paise (Razorpay uses the smallest currency unit)
        currency: "INR",
        name: "Journey of Joy",
        description: "Tour Payment",
        image: "https://example.com/your_logo", // Optional: your company logo
        order_id: "order_IluGWxBm9U8zJ8", // Replace with the actual order ID you get from your server
        handler: function (response) {
          alert(`Payment successful!\nPayment ID: ${response.razorpay_payment_id}\nOrder ID: ${response.razorpay_order_id}\nSignature: ${response.razorpay_signature}`);
          handlePayment(response); // Call handlePayment with Razorpay response
        },
        prefill: {
          name: "",
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

      const rzp1 = new window.Razorpay(options); // Use window.Razorpay to access Razorpay
      rzp1.on('payment.failed', function (response) {
        alert(`Payment failed! Error: ${response.error.description}`);
      });

      rzp1.open();
    } else {
      console.error("Razorpay is not loaded");
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
        <button type="button" className="btn btn-success mt-3" onClick={initiateRazorpay}>
          Pay with Razorpay
        </button>
        {error && <p className="text-danger mt-3">{error}</p>}
      </form>
    </div>
  );
}

export default PaymentPage;
