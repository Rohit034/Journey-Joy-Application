package com.app.journeyjoy.service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.journeyjoy.custom_exceptions.ResourceNotFoundException;
import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.TourDTO;
import com.app.journeyjoy.entities.Hotel;
import com.app.journeyjoy.entities.Packages;
import com.app.journeyjoy.entities.Tour;
import com.app.journeyjoy.entities.User;
import com.app.journeyjoy.repository.HotelRepository;
import com.app.journeyjoy.repository.TourRepository;
import com.app.journeyjoy.repository.UserRepository;

@Service
@Transactional
public class TourServiceImpl implements TourService {
	@Autowired
	private TourRepository tourRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public List<TourDTO> getallTour() {
		return tourRepository.findAll().stream().map(tour -> modelMapper.map(tour, TourDTO.class))
				.collect(Collectors.toList());
	}



	@Override
	public ApiResponse deleteTour(Long id) {
		if (tourRepository.existsById(id)) {
			tourRepository.deleteById(id);
			return new ApiResponse("Tour is deleted");
		}
		return new ApiResponse("Tour id is not valid");
	}

	@Override
	public ApiResponse createTour(TourDTO tourdto,Long hotelId) {
		User u = userRepository.findById(tourdto.user_id)
				.orElseThrow(() -> new ResourceNotFoundException("invalid user_id"));
		
		Hotel hotel=hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel id not found"));
		Tour tour = modelMapper.map(tourdto, Tour.class);
		tour.setUsers(u);
		Double basepriceforpackage=getPriceBasedOnPackage(tour.getPackages());//to get the package price 
		
		Double hotelpricebyrating=hotel.getStarRating()*50.0;//to get the hotel price by rating 
		
		int days=(int)ChronoUnit.DAYS.between(tour.getStartdate(),tour.getEndDate());
		tour.setPrice(basepriceforpackage+hotelpricebyrating*days);
		tourRepository.save(tour);
		return new ApiResponse("new tour is created");
	}
	
	
	private Double getPriceBasedOnPackage(Packages packageType) {
	    switch (packageType) {
	        case ADVENTURE:
	            return 150.0;
	        case CULTURAL:
	            return 180.0;
	        case CRUISE:
	            return 250.0;
	        case FAMILY:
	            return 120.0;
	        case ROMANTIC:
	            return 220.0;
	        case LUXURY:
	            return 300.0;
	        default:
	            return 0.0;
	    }
	}


}
