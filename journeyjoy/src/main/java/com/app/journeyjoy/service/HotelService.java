package com.app.journeyjoy.service;

import java.util.List;

import com.app.journeyjoy.dto.HotelDTO;
import com.app.journeyjoy.entities.Hotel;

public interface HotelService {
	List<HotelDTO> getallhotel();

	Hotel addNewHotel(Hotel newhotel);

	String deleteHotel(Long id);

	Hotel updateHotel(Hotel hotel);

}
