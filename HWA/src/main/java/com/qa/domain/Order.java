package com.qa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
//    private Long customerId;
//    private Long orderValue;

    @OneToMany (mappedBy = "order", fetch = FetchType.LAZY)
    private List<Customer> customers = new ArrayList<>();

    public Order() {
    }

//    public Order(Long customerId) {
//        this.customerId = customerId;
//    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

//    public Long getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(Long customerId) {
//        this.customerId = customerId;
//    }


    public List<Customer> getCustomers() { return customers; }

    public void setCustomers(List<Customer> customers) { this.customers = customers; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getOrderId(), order.getOrderId()) &&
                Objects.equals(getCustomers(), order.getCustomers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getCustomers());
    }
}
