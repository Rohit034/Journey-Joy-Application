package com.app.journeyjoy.service;

import java.util.List;

import com.app.journeyjoy.dto.DestinationDTO;
import com.app.journeyjoy.entities.Destination;

public interface TourService {
	
	List<DestinationDTO> getallDestination();

	Destination addNewHotel(Destination newDestination);

	String deleteDestination(Long id);

	Destination updateDestination(Destination Destination);

}
