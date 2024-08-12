package com.app.journeyjoy.service;

import java.util.List;

import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.TourDTO;
import com.app.journeyjoy.entities.Hotel;
import com.app.journeyjoy.entities.Packages;

public interface TourService {
	List<TourDTO> getallTour();

	//ApiResponse selectTour(TourDTO tour);

	ApiResponse deleteTour(Long id);

	ApiResponse createTour(TourDTO tourdto,Long hotelId);
	
	
}
