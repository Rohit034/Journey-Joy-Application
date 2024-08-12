package com.app.journeyjoy.service;

import java.util.List;

import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.HotelDTO;
import com.app.journeyjoy.entities.Hotel;

public interface HotelService {
	List<HotelDTO> getallhotel();

	ApiResponse addNewHotel(HotelDTO hotel);

	ApiResponse deleteHotel(Long id);

	ApiResponse updateHotel(HotelDTO hotel);
	
	List<Hotel> findHotelByDestinationId(Long destinationId);
}
