package com.app.journeyjoy.dto;

import lombok.ToString;

@ToString
public class PaymentRequest {
	 private Double amount;
	    private Long bookingId;
	    private String customerPhone;
	    private String customerEmail;

	    // Getters and Setters
	    public Double getAmount() {
	        return amount;
	    }

	    public void setAmount(Double amount) {
	        this.amount = amount;
	    }

	    public Long getBookingId() {
	        return bookingId;
	    }

	    public void setBookingId(Long bookingId) {
	        this.bookingId = bookingId;
	    }

	    public String getCustomerPhone() {
	        return customerPhone;
	    }

	    public void setCustomerPhone(String customerPhone) {
	        this.customerPhone = customerPhone;
	    }

	    public String getCustomerEmail() {
	        return customerEmail;
	    }

	    public void setCustomerEmail(String customerEmail) {
	        this.customerEmail = customerEmail;
	    }
}
