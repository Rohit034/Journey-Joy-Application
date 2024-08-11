package com.app.journeyjoy.dto;

import java.time.LocalDate;

import com.app.journeyjoy.entities.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {

	public Long id;
	public double amount;
	public LocalDate paymentdate;
	public String paymentmethod;
	public PaymentStatus paymentstatus;
	public Long booking_id;

}
