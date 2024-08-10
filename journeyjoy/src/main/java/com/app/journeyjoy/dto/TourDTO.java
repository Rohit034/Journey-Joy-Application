package com.app.journeyjoy.dto;

import java.time.LocalDate;

import com.app.journeyjoy.entities.Packages;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TourDTO {

	public Long id;
	public Packages packages;
	public LocalDate StartDate;
	public LocalDate EndDate;
	public double price;
	@JsonProperty(access = Access.WRITE_ONLY)
	public Long user_id;

}
