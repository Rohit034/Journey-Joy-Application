package com.app.journeyjoy.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDTO {

	public Long id;
	public LocalDate BookingDate;
	public String PaymentStatus;

}
