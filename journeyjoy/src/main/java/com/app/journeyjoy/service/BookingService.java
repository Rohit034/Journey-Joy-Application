package com.app.journeyjoy.service;

import java.util.List;

import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.BookingDTO;


public interface BookingService {

	List<BookingDTO> getallbooking();
	ApiResponse createBooking(BookingDTO bookingDTO);
	
}
