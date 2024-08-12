package com.app.journeyjoy.service;

import java.util.List;

import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.TourDTO;

public interface TourService {
	List<TourDTO> getallTour();

	ApiResponse selectTour(TourDTO tour);

	ApiResponse deleteTour(Long id);

	ApiResponse createTour(TourDTO tourdto);
	
}
