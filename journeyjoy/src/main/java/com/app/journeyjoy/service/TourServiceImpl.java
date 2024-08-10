package com.app.journeyjoy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.journeyjoy.custom_exceptions.ResourceNotFoundException;
import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.TourDTO;
import com.app.journeyjoy.entities.Tour;
import com.app.journeyjoy.entities.User;
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

	@Override
	public List<TourDTO> getallTour() {
		return tourRepository.findAll().stream().map(tour -> modelMapper.map(tour, TourDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addNewTour(TourDTO newtour) {

		User u = userRepository.findById(newtour.user_id)
				.orElseThrow(() -> new ResourceNotFoundException("invalid user_id"));

		Tour tour = modelMapper.map(newtour, Tour.class);

		tour.setUsers(u);

		tourRepository.save(tour);

		return new ApiResponse("New Tour added");
	}

	@Override
	public ApiResponse deleteTour(Long id) {
		if (tourRepository.existsById(id)) {
			tourRepository.deleteById(id);
			return new ApiResponse("Tour is deleted");
		}
		return new ApiResponse("Tour id is not valid");
	}

}
