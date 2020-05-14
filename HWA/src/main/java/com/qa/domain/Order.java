package com.qa.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Long customerId;
    private Long orderValue;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    @ManyToOne(targetEntity = Orderline.class)
    private Orderline orderline;

    public Order() {
    }

    public Order(Long orderValue) {
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

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getOrderId(), order.getOrderId()) &&
                Objects.equals(getCustomerId(), order.getCustomerId()) &&
                Objects.equals(getOrderValue(), order.getOrderValue()) &&
                Objects.equals(getCustomer(), order.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getCustomerId(), getOrderValue(), getCustomer());
    }
}
