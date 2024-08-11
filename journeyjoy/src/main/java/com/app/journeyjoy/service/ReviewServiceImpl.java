package com.app.journeyjoy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.journeyjoy.dto.ReviewsDTO;
import com.app.journeyjoy.repository.ReviewsRepository;

@Service
@Transactional

public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewsRepository repositoryReviewsRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ReviewsDTO> getallReview() {
		return repositoryReviewsRepository.findAll()
				.stream()
				.map(review->
				modelMapper.map(review, ReviewsDTO.class))
				.collect(Collectors.toList());
	}

}
