package com.qa.dto;

import java.util.List;
import java.util.Objects;

public class OrderDTO {

    private Long orderId;

    private List<CustomerDTO> customers;

    public OrderDTO() {
    }

    public OrderDTO( List<CustomerDTO> customers) {
        super();
        this.customers = customers;
    }

    public Long getOrderId() { return orderId; }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDTO> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", customers=" + customers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(getOrderId(), orderDTO.getOrderId()) &&
                Objects.equals(getCustomers(), orderDTO.getCustomers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getCustomers());
    }
}
