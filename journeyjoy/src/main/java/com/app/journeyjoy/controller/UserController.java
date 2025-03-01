package com.app.journeyjoy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.AuthDTO;
import com.app.journeyjoy.dto.BookingDTO;
import com.app.journeyjoy.dto.BookingRespDTO;
import com.app.journeyjoy.dto.CashfreePaymentResponse;
import com.app.journeyjoy.dto.PaymentDTO;
import com.app.journeyjoy.dto.PaymentRequest;
import com.app.journeyjoy.dto.ReviewsDTO;
import com.app.journeyjoy.dto.SigninResponse;
import com.app.journeyjoy.dto.TourDTO;
import com.app.journeyjoy.dto.UserRespDTO;
import com.app.journeyjoy.entities.Destination;
import com.app.journeyjoy.security.CustomUserDetails;
import com.app.journeyjoy.security.JwtUtils;
import com.app.journeyjoy.service.BookingService;
import com.app.journeyjoy.service.DestinationService;
import com.app.journeyjoy.service.HotelService;
import com.app.journeyjoy.service.PaymentService;
import com.app.journeyjoy.service.ReviewService;
import com.app.journeyjoy.service.TourService;
import com.app.journeyjoy.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private TourService tourService;

	@Autowired
	private DestinationService destinationService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private AuthenticationManager authMgr;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> userSignIn(@RequestBody @Valid AuthDTO request) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
					request.getPassword());
			// 2. invoke auth mgr's authenticate method;
			Authentication verifiedToken = authMgr.authenticate(token);
			// => authentication n authorization successful !
			CustomUserDetails custuser = (CustomUserDetails) verifiedToken.getPrincipal();
			// 3. In case of successful auth, create JWT n send it to the clnt in response
			SigninResponse resp = new SigninResponse(jwtUtils.generateJwtToken(verifiedToken),
					new UserRespDTO(custuser.getUser()));
			// UserRespDTO respDto = userService.authenticateUser(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(resp);

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PostMapping("/userRegistration")
	public ResponseEntity<?> userRegistration(@RequestBody UserRespDTO udto) {
		try {
			UserRespDTO registeruser = userService.addNewUser(udto);
			return ResponseEntity.status(HttpStatus.CREATED).body(registeruser);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/createtour")
	public ResponseEntity<?> bookTour(@RequestBody TourDTO tourdto, @RequestParam Long hotelId) {
		try {
			if (hotelId == null) {
				throw new IllegalArgumentException("Hotel ID must not be null.");
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(tourService.createTour(tourdto, hotelId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/searchDestination")
	public ResponseEntity<?> searchDestination(@RequestParam String location) {
		try {
			List<Destination> destination = destinationService.searchDestination(location);
			return ResponseEntity.ok(destination);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/addTourReview")
	public ResponseEntity<?> addTourReview(@RequestBody ReviewsDTO reviewDTO, @RequestParam Long tourId) {
		try {
			ApiResponse response = reviewService.addTourReview(reviewDTO, tourId);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/makeBooking")
	public ResponseEntity<?> makeBooking(@RequestBody BookingDTO bookingDTO) {
		try {

			BookingRespDTO response = bookingService.createBooking(bookingDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/gethotels")
	public ResponseEntity<?> getHotelBydestinationid(@RequestParam Long destinationid) {
		try {

			return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.findHotelByDestinationId(destinationid));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/makePayment")
	public ResponseEntity<?> makePayment(@RequestBody PaymentDTO paymentDTO, @RequestParam Long bookingId) {
		try {
			String response = paymentService.paymentProcess(paymentDTO, bookingId);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}

	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/createOrder")
	public ResponseEntity<?> createPaymentOrder(@RequestBody @Valid PaymentRequest paymentRequest) {
		try {
			String orderId = paymentService.createOrder(paymentRequest);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ApiResponse("Order created successfully. Order ID: " + orderId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/payment/response")
	public ResponseEntity<?> handlePaymentResponse(@RequestBody CashfreePaymentResponse response) {
	    try {
	        // Process the response (e.g., verify the payment status, update payment record)
	        paymentService.handlePaymentResponse(response);
	        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Payment status updated successfully."));
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
	    }
	}


}
