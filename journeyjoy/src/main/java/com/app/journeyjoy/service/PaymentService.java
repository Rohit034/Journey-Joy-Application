package com.app.journeyjoy.service;

import java.util.List;

import com.app.journeyjoy.dto.ApiResponse;
import com.app.journeyjoy.dto.CashfreePaymentResponse;
import com.app.journeyjoy.dto.PaymentDTO;
import com.app.journeyjoy.dto.PaymentRequest;

public interface PaymentService {
	List<PaymentDTO> getallpayment();
	String paymentProcess(PaymentDTO paymentDTO,Long bookingid);
	String createOrder(PaymentRequest paymentRequest);
	void handlePaymentResponse(CashfreePaymentResponse response);
}
