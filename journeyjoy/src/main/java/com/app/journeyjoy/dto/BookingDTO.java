package com.app.journeyjoy.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDTO {

	public Long id;
	public LocalDate BookingDate;
	public String PaymentStatus;
	@JsonProperty(access = Access.WRITE_ONLY)
	public Long tour_id;
	

}
