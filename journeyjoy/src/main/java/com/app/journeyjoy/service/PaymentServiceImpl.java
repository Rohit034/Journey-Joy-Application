package com.app.journeyjoy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.journeyjoy.custom_exceptions.ResourceNotFoundException;
import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.PaymentDTO;
import com.app.journeyjoy.entities.Booking;
import com.app.journeyjoy.entities.Payment;
import com.app.journeyjoy.repository.BookingRepository;
import com.app.journeyjoy.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BookingRepository bookingRepository;

    private RazorpayClient razorpayClient;

    public PaymentServiceImpl() throws Exception {
        this.razorpayClient = new RazorpayClient("YOUR_KEY_ID", "YOUR_KEY_SECRET");
    }

    @Override
    public List<PaymentDTO> getallpayment() {
        return paymentRepository.findAll()
                .stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public ApiResponse createRazorpayOrder(PaymentDTO paymentDTO) {
        // Fetch the booking information using bookingId from the paymentDTO
        Booking booking = bookingRepository.findById(paymentDTO.getBooking_id())
                .orElseThrow(() -> new RuntimeException("Booking ID not found!"));

        try {
            // Create Razorpay Order
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", paymentDTO.getAmount() * 100); // Amount in paise
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "receipt#" + paymentDTO.getBooking_id());
            JSONObject notes = new JSONObject();
            notes.put("notes_key_1", "Booking ID: " + paymentDTO.getBooking_id());
            orderRequest.put("notes", notes);

            Order order = razorpayClient.orders.create(orderRequest);

            // Save payment details in the database
            Payment payment = modelMapper.map(paymentDTO, Payment.class);
            payment.setBookings(booking);
            payment.setId(order.get("id"));
            paymentRepository.save(payment);

            return new ApiResponse("Razorpay order created successfully!", order.get("id"));
        } catch (Exception e) {
            throw new RuntimeException("Error creating Razorpay order: " + e.getMessage(), e);
        }
    }
    @Override
    public ApiResponse paymentProcess(PaymentDTO paymentDTO, Long bookingId) {
        // Fetch booking details
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking ID not found!"));

        // Create Razorpay Order
        try {
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", paymentDTO.getAmount() * 100); // Amount in paise
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "receipt#" + bookingId);
            JSONObject notes = new JSONObject();
            notes.put("notes_key_1", "Booking ID: " + bookingId);
            orderRequest.put("notes", notes);

            Order order = razorpayClient.orders.create(orderRequest);

            // Save payment details
            Payment payment = modelMapper.map(paymentDTO, Payment.class);
            payment.setBookings(booking);
            payment.setId(order.get("id"));
            paymentRepository.save(payment);

            return new ApiResponse("Razorpay order created successfully! Order ID: " + order.get("id"));
        } catch (Exception e) {
            throw new RuntimeException("Error creating Razorpay order: " + e.getMessage(), e);
        }
    }
}
