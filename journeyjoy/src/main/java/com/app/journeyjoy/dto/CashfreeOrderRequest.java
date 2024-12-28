package com.app.journeyjoy.dto;

public class CashfreeOrderRequest {

    private String orderId;
    private String orderAmount;
    private String orderCurrency;
    private String customerEmail;
    private String customerPhone;
    private String returnUrl;
    private String version; // Add the version field

    // Updated constructor to include version
    public CashfreeOrderRequest(String orderId, String orderAmount, String orderCurrency, 
                                 String customerEmail, String customerPhone, String returnUrl, String version) {
        this.orderId = orderId;
        this.orderAmount = orderAmount;
        this.orderCurrency = orderCurrency;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.returnUrl = "http://localhost:8443/users/payment/response";
        this.version = version; // Initialize the version field
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
