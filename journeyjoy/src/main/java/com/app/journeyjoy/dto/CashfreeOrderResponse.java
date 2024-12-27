package com.app.journeyjoy.dto;

public class CashfreeOrderResponse {
	 private String orderId;
     private String paymentLink;
		public String getOrderId() {
			return orderId;
		}
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		public String getPaymentLink() {
			return paymentLink;
		}
		public void setPaymentLink(String paymentLink) {
			this.paymentLink = paymentLink;
		}
}
