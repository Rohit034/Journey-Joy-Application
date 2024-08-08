package com.app.journeyjoy.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourDTO {

	public Long id;
	public LocalDate StartDate;
	public LocalDate EndDate;
	public String packages;
	public double price;

}
