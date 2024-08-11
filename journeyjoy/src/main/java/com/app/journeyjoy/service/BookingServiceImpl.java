package com.app.journeyjoy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.journeyjoy.dto.BookingDTO;
import com.app.journeyjoy.repository.BookingRepository;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<BookingDTO> getallbooking() {
		return bookingRepository.findAll().stream().map(booking -> modelMapper.map(booking, BookingDTO.class))
				.collect(Collectors.toList());
	}

}
