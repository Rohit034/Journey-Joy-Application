//package com.app.journeyjoy.service;
//
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.app.journeyjoy.custom_exceptions.ResourceNotFoundException;
//import com.app.journeyjoy.dto.PaymentDTO;
//import com.app.journeyjoy.dto.PaymentRequest;
//import com.app.journeyjoy.entities.Booking;
//import com.app.journeyjoy.entities.Payment;
//import com.app.journeyjoy.entities.PaymentStatus;
//import com.app.journeyjoy.entities.Tour;
//import com.app.journeyjoy.entities.User;
//import com.app.journeyjoy.repository.BookingRepository;
//import com.app.journeyjoy.repository.PaymentRepository;
//import com.app.journeyjoy.repository.TourRepository;
//import com.app.journeyjoy.repository.UserRepository;
//import com.cashfree.*;
//import com.cashfree.model.*;
//
//import okhttp3.OkHttpClient;
//
//@Service
//@Transactional
//public class PaymentServiceImpl implements PaymentService {
//
//    @Autowired
//    private PaymentRepository paymentRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private BookingRepository bookingRepository;
//    
//    @Autowired
//    private UserRepository userRepository;
//    
//    @Autowired
//    private TourRepository tourRepository;
//    
//    @Value("${cashfree.api.key}")
//    private String clientId;
//
//    @Value("${cashfree.secret.key}")
//    private String clientSecret;
//
//    @Value("${cashfree.environment}")
//    private String environment;
//    
//    private static final String X_API_VERSION="2022-09-01";
//    @Override
//    public List<PaymentDTO> getallpayment() {
//        return paymentRepository.findAll()
//                .stream()
//                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String createOrder(PaymentRequest paymentRequest) {
//        // Fetch booking details using bookingId
//        Booking booking = bookingRepository.findById(paymentRequest.getBookingId())
//                .orElseThrow(() -> new ResourceNotFoundException("Booking ID not found!"));
//
//        try {
//        	Tour tour=tourRepository.findById(booking.getTours().getId())
//        			.orElseThrow(()-> new ResourceNotFoundException("Tour not Found!"));
//        	
//        	User user=userRepository.findById(tour.getUsers().getId())
//        			.orElseThrow(()->new ResourceNotFoundException("User not found!"));
//            // Convert PaymentRequest to PaymentDTO
//            PaymentDTO paymentDTO = new PaymentDTO();
//            paymentDTO.setAmount(paymentRequest.getAmount());
//            paymentDTO.setCustomerPhone(user.getPhoneno());
//            paymentDTO.setCustomerEmail(user.getEmail());
//
//            // Process the payment request
//            return paymentProcess(paymentDTO, booking.getId());
//        } catch (Exception e) {
//            throw new RuntimeException("Error creating order: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public String paymentProcess(PaymentDTO paymentDTO, Long bookingId) {
//        // Fetch booking details
//        Booking booking = bookingRepository.findById(bookingId)
//                .orElseThrow(() -> new ResourceNotFoundException("Booking ID not found!"));
//
//        try {
//            // Map DTO to Payment entity
//            Payment payment = modelMapper.map(paymentDTO, Payment.class);
//            payment.setBookings(booking);
//            payment.setPaymentstatus(PaymentStatus.PENDING);
//
//            // Save the payment record in the database
//            paymentRepository.save(payment);
//
//            // Set up customer details for Cashfree
//            CustomerDetails customerDetails = new CustomerDetails();
//            customerDetails.setCustomerId(String.valueOf(booking.getId()));
//            customerDetails.setCustomerPhone(paymentDTO.getCustomerPhone());
//            customerDetails.setCustomerEmail(paymentDTO.getCustomerEmail());
//           
//            Cashfree.XClientId = clientId;
//            Cashfree.XClientSecret = clientSecret;
//            Cashfree.XEnvironment = environment.equalsIgnoreCase("SANDBOX")
//                    ? Cashfree.SANDBOX
//                    : Cashfree.PRODUCTION;
//
//            // Create an instance of Cashfree
//            Cashfree cashfree = new Cashfree();
//            // Create Cashfree order
//            CreateOrderRequest request = new CreateOrderRequest();
//            request.setOrderAmount(paymentDTO.getAmount());
//            request.setOrderCurrency("INR");
//            request.setCustomerDetails(customerDetails);
//   
//
//     
//            
//
//            // Generate unique order ID
//            String uniqueOrderId = String.valueOf(payment.getId());
//
//            // Call PGCreateOrder
//            ApiResponse<OrderEntity> response = cashfree.PGCreateOrder(
//                    X_API_VERSION, // API version
//                    request,       // Order request object
//                    null,          // Optional parameter: client secret (nullable)
//                    null,          // Optional parameter: UUID (nullable)
//                    null           // Optional parameter: OkHttpClient (nullable)
//            );
//            // Retrieve the order ID from the response
//            String orderId = response.getData().getOrderId();
//            payment.setOrderId(orderId);
//            payment.setPaymentstatus(PaymentStatus.COMPLETED);
//
//            // Update payment record with order ID
//            paymentRepository.save(payment);
//
//            return orderId;
//
//        } catch (ApiException e) {
//            // Handle Cashfree API errors
//            throw new RuntimeException("Error creating order with Cashfree: " + e.getMessage(), e);
//        } catch (Exception e) {
//            // Handle other exceptions
//            throw new RuntimeException("Error processing payment: " + e.getMessage(), e);
//        }
//    }
//}
package com.app.journeyjoy.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.app.journeyjoy.custom_exceptions.ResourceNotFoundException;
import com.app.journeyjoy.dto.CashfreeOrderResponse;
import com.app.journeyjoy.dto.CashfreePaymentResponse;
import com.app.journeyjoy.dto.PaymentDTO;
import com.app.journeyjoy.dto.PaymentRequest;
import com.app.journeyjoy.entities.Booking;
import com.app.journeyjoy.entities.Payment;
import com.app.journeyjoy.entities.PaymentStatus;
import com.app.journeyjoy.entities.Tour;
import com.app.journeyjoy.entities.User;
import com.app.journeyjoy.repository.BookingRepository;
import com.app.journeyjoy.repository.PaymentRepository;
import com.app.journeyjoy.repository.TourRepository;
import com.app.journeyjoy.repository.UserRepository;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TourRepository tourRepository;

    @Value("${cashfree.api.key}")
    private String clientId;

    @Value("${cashfree.secret.key}")
    private String clientSecret;

    @Value("${cashfree.environment}")
    private String environment;

    private static final String SANDBOX_URL = "https://sandbox.cashfree.com/pg/orders";
    private static final String PRODUCTION_URL = "https://api.cashfree.com/pg/orders";

    @Override
    public List<PaymentDTO> getallpayment() {
        return paymentRepository.findAll()
                .stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public String createOrder(PaymentRequest paymentRequest) {
    	
    	System.out.println("Received paymentRequest: " + paymentRequest);
        Booking booking = bookingRepository.findById(paymentRequest.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking ID not found!"));

        try {
            Tour tour = tourRepository.findById(booking.getTours().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tour not Found!"));

            User user = userRepository.findById(tour.getUsers().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setAmount(paymentRequest.getAmount());
            paymentDTO.setCustomerPhone(user.getPhoneno());
            paymentDTO.setCustomerEmail(user.getEmail());
            System.out.println("Received paymentRequest: " + paymentRequest);
            return paymentProcess(paymentDTO, booking.getId());
        } catch (Exception e) {
            throw new RuntimeException("Error creating order: " + e.getMessage(), e);
        }
    }

    @Override
    public String paymentProcess(PaymentDTO paymentDTO, Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking ID not found!"));

        try {
            Payment payment = modelMapper.map(paymentDTO, Payment.class);
            payment.setBookings(booking);
            payment.setPaymentstatus(PaymentStatus.PENDING);

            paymentRepository.save(payment);

            // Prepare Cashfree order request
            String url = environment.equalsIgnoreCase("SANDBOX") ? SANDBOX_URL : PRODUCTION_URL;

            String uniqueOrderId = "Order_" + UUID.randomUUID();

            RestTemplate restTemplate = new RestTemplate();
            var requestBody = new com.app.journeyjoy.dto.CashfreeOrderRequest(
                    uniqueOrderId,
                    String.valueOf(paymentDTO.getAmount()),
                    "INR",
                    paymentDTO.getCustomerEmail(),
                    paymentDTO.getCustomerPhone(),
                    clientId,
                    "2023-08-01"
            );

            var headers = new org.springframework.http.HttpHeaders();
            headers.set("X-Client-Id", clientId);
            headers.set("X-Client-Secret", clientSecret);

            var httpEntity = new org.springframework.http.HttpEntity<>(requestBody, headers);

            var response = restTemplate.postForObject(url, httpEntity, CashfreeOrderResponse.class);

            if (response == null || response.getOrderId() == null) {
                throw new RuntimeException("Failed to create Cashfree order");
            }

            // Update payment status
            payment.setOrderId(response.getOrderId());
            payment.setPaymentstatus(PaymentStatus.COMPLETED);
            paymentRepository.save(payment);

            return response.getOrderId();

        } catch (Exception e) {
            throw new RuntimeException("Error processing payment: " + e.getMessage(), e);
        }
    }
    @Override
    public void handlePaymentResponse(CashfreePaymentResponse response) {
        // Process the payment response from Cashfree
        // Example: Verify the payment status and update the payment record in the database
        String orderId = response.getOrderId();
        Payment payment = paymentRepository.findByOrderId(orderId);
        if (payment == null) {
            throw new RuntimeException("Payment not found for Order ID: " + orderId);
        }

        if ("SUCCESS".equals(response.getPaymentStatus())) {
            payment.setPaymentstatus(PaymentStatus.COMPLETED);
        } else {
            payment.setPaymentstatus(PaymentStatus.FAILED);
        }
        
        paymentRepository.save(payment);
    }

}

