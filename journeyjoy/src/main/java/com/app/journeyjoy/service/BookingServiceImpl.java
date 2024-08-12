package com.app.journeyjoy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.journeyjoy.custom_exceptions.ResourceNotFoundException;
import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.BookingDTO;
import com.app.journeyjoy.entities.Booking;
import com.app.journeyjoy.entities.Tour;
import com.app.journeyjoy.entities.User;
import com.app.journeyjoy.repository.BookingRepository;
import com.app.journeyjoy.repository.TourRepository;
import com.app.journeyjoy.repository.UserRepository;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TourRepository tourRepository;

	@Override
	public List<BookingDTO> getallbooking() {
		return bookingRepository.findAll().stream().map(booking -> modelMapper.map(booking, BookingDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse createBooking(BookingDTO bookingDTO) {
        Tour tour = tourRepository.findById(bookingDTO.getTour_id())
                .orElseThrow(() -> new ResourceNotFoundException("Tour id not found"));

        Booking booking =modelMapper.map(bookingDTO, Booking.class);
        booking.setTours(tour);
        bookingRepository.save(booking);
        return new ApiResponse("Booking created successfully");
	}

}
