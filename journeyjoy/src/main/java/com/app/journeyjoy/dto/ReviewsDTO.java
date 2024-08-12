package com.app.journeyjoy.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewsDTO {
	@JsonProperty(access = Access.READ_ONLY)
	public Long id;
	public LocalDate reviewdate;
	public String comment;
	public int rating;
	

}
