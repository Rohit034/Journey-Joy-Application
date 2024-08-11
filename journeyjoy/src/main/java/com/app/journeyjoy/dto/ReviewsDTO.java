package com.app.journeyjoy.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewsDTO {
	
	public Long id;
	public LocalDate reviewdate;
	public String comment;
	public int rating;
	

}
