package com.app.journeyjoy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.BookingDTO;
import com.app.journeyjoy.dto.DestinationDTO;
import com.app.journeyjoy.dto.HotelDTO;
import com.app.journeyjoy.dto.PaymentDTO;
import com.app.journeyjoy.dto.ReviewsDTO;
import com.app.journeyjoy.dto.TourDTO;
import com.app.journeyjoy.repository.PaymentRepository;
import com.app.journeyjoy.service.BookingService;
import com.app.journeyjoy.service.DestinationService;
import com.app.journeyjoy.service.HotelService;
import com.app.journeyjoy.service.PaymentService;
import com.app.journeyjoy.service.ReviewService;
import com.app.journeyjoy.service.TourService;

@RestController
@RequestMapping("/Admin")
public class AdminController {

	@Autowired // byType
	private HotelService hotelservice;
	@Autowired
	private DestinationService destinationService;
	@Autowired
	private TourService tourService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private ReviewService reviewService;

//	Tour Operations

	@GetMapping("/getAllTour")
	public List<TourDTO> listAllTour() {
		return tourService.getallTour();
	}


	@DeleteMapping("/{DeleteTourid}")
	public ApiResponse deleteTour(@PathVariable Long DeleteTourid) {

		return tourService.deleteTour(DeleteTourid);

	}

//	Destination operations

	@GetMapping("/getAllDestination")
	public List<DestinationDTO> listAllDestination() {
		return destinationService.getallDestination();
	}

	@PostMapping("/newDestination")
	public ApiResponse addNewDestination(@RequestBody DestinationDTO Dest) {
		System.out.println("in add new category " + Dest);
		return destinationService.addNewDestination(Dest);
	}

	@DeleteMapping("/{DeleteDestinationid}")
	public ApiResponse deleteDestination(@PathVariable Long DeleteDestinationid) {

		return tourService.deleteTour(DeleteDestinationid);

	}

//	Hotel operations

	@GetMapping("/allhotel")
	public List<HotelDTO> listAllHotels() {

		return hotelservice.getallhotel();
	}

	@PostMapping("/newHotel")
	public ApiResponse addNewhotel(@RequestBody HotelDTO hotel) {

		return hotelservice.addNewHotel(hotel);
	}

	@DeleteMapping("/{hotelId}")
	public ApiResponse deleteHotelDetails(@PathVariable Long hotelId) {
		System.out.println("in del category " + hotelId);
		return hotelservice.deleteHotel(hotelId);

	}

	@PutMapping("/updatehotel")
	public ApiResponse updateHotelDetails(@RequestBody HotelDTO category) {
		System.out.println("in update " + category);
		return hotelservice.updateHotel(category);
	}

//	booking operation

	@GetMapping("/allBoking")
	public List<BookingDTO> getallBooking() {
		return bookingService.getallbooking();
	}

//  Payment operation

	@GetMapping("/allPayments")
	public List<PaymentDTO> getallPayments() {
		return paymentService.getallpayment();
	}

//	Review operation 
	
	@GetMapping("/allReviews")
	public List<ReviewsDTO> getallReviews(){
		return reviewService.getallReview();
	}
}
