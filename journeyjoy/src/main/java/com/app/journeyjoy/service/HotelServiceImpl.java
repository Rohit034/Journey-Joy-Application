package com.app.journeyjoy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.journeyjoy.custom_exceptions.ResourceNotFoundException;
import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.HotelDTO;
import com.app.journeyjoy.entities.Destination;
import com.app.journeyjoy.entities.Hotel;
import com.app.journeyjoy.entities.Tour;
import com.app.journeyjoy.entities.User;
import com.app.journeyjoy.repository.DestinationRepository;
import com.app.journeyjoy.repository.HotelRepository;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

	@Autowired
	public HotelRepository hotelRepository;
	@Autowired
	public ModelMapper modelMapper;
	@Autowired
	public DestinationRepository destinationRepository;

	@Override
	public List<HotelDTO> getallhotel() {
		return hotelRepository.findAll().stream().map(hotel -> modelMapper.map(hotel, HotelDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addNewHotel(HotelDTO newhotel) {
		Destination Dest = destinationRepository.findById(newhotel.destination_id)
				.orElseThrow(() -> new ResourceNotFoundException("invalid destination_id"));

		Hotel hotel = modelMapper.map(newhotel, Hotel.class);

		hotel.setDestinations(Dest);

		hotelRepository.save(hotel);

		return new ApiResponse("New Tour added");
	}

	@Override
	public ApiResponse deleteHotel(Long id) {
		if (hotelRepository.existsById(id)) {
			// API of CrudRepo - public void deleteById(ID id)
			hotelRepository.deleteById(id);
			return new ApiResponse("Hotel deleted");
		}
		return new ApiResponse("Invalid Hotel ID");

	}

	@Override
	public ApiResponse updateHotel(HotelDTO hotel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> findHotelByDestinationId(Long destinationId) {
		return hotelRepository.findByDestinations(destinationId);
	}

}
