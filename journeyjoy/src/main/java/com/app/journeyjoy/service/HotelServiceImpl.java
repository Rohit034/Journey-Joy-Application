package com.app.journeyjoy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.journeyjoy.dto.HotelDTO;
import com.app.journeyjoy.entities.Hotel;
import com.app.journeyjoy.repository.HotelRepository;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

	@Autowired
	public HotelRepository hotelRepository;
	@Autowired
	public ModelMapper modelmapper;
	
	
	@Override
	public List<HotelDTO> getallhotel() {
		return hotelRepository.findAll().
				stream().
				map(hotel->
				modelmapper.map(hotel, HotelDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Hotel addNewHotel(Hotel newhotel) {
		// TODO Auto-generated method stub
		return hotelRepository.save(newhotel);
	}

	@Override
	public String deleteHotel(Long id) {
		if (hotelRepository.existsById(id)) {
			// API of CrudRepo - public void deleteById(ID id)
			hotelRepository.deleteById(id);
			return "hotel  deleted";
		}
		return "deleting hotel details failed : Invalid hotel ID";
	
	}

	@Override
	public Hotel updateHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		return hotelRepository.save(hotel);
	}

}
