package com.app.journeyjoy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.journeyjoy.dto.DestinationDTO;
import com.app.journeyjoy.entities.Destination;
import com.app.journeyjoy.repository.DestinationRepository;

@Service
@Transactional
public class DestinationServiceImpl implements DestinationService {

	@Autowired
	public DestinationRepository destinationRepository;
	@Autowired
	public ModelMapper modelMapper;
	
	@Override
	public List<DestinationDTO> getallDestination() {
		return destinationRepository.findAll()
				.stream()
				.map(destination ->
				modelMapper.map(destination, DestinationDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Destination addNewHotel(Destination newDestination) {
		// TODO Auto-generated method stub
		return destinationRepository.save(newDestination);
	}

	@Override
	public String deleteDestination(Long id) {
		if (destinationRepository.existsById(id)) {
			// API of CrudRepo - public void deleteById(ID id)
			destinationRepository.deleteById(id);
			return "destination  deleted";
		}
		return "deleting destination details failed : Invalid dstination ID";
	
	}

	@Override
	public Destination updateDestination(Destination Destination) {
		// TODO Auto-generated method stub
		return destinationRepository.save(Destination);
	}

}
