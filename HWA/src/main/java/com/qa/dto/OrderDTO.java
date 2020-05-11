package com.qa.dto;

public class OrderDTO {

    private Long orderId;
    private Long customerId;
    private Long orderValue;

    public OrderDTO() {

    }

    public OrderDTO(Long customerId, Long orderValue) {
        this.customerId = customerId;
        this.orderValue = orderValue;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Long orderValue) {
        this.orderValue = orderValue;
    }
}
