package com.app.journeyjoy.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {

	public Long id;
	public double amount;
	public LocalDate paymentdate;
	public String paymentmethod;
	public String paymentstatus;

}
