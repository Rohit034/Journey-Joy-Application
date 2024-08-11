package com.app.journeyjoy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.journeyjoy.dto.PaymentDTO;
import com.app.journeyjoy.repository.PaymentRepository;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<PaymentDTO> getallpayment() {
		return paymentRepository.findAll()
				.stream()
				.map(payment->
				modelMapper.map(payment, PaymentDTO.class))
				.collect(Collectors.toList());
	}

}
