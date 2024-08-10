package com.app.journeyjoy.service;

import java.util.List;

import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.DestinationDTO;

public interface DestinationService {

	List<DestinationDTO> getallDestination();

	ApiResponse addNewDestination(DestinationDTO newDestination);

	ApiResponse deleteDestination(Long id);

	ApiResponse updateDestination(DestinationDTO Destination);
}
