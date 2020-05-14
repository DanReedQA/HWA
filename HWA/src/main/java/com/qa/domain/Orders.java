package com.qa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;


    @OneToMany (mappedBy = "orders", fetch = FetchType.LAZY)
    private List<Customer> customers = new ArrayList<>();

    public Orders() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<Customer> getCustomers() { return customers; }

    public void setCustomers(List<Customer> customers) { this.customers = customers; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        Orders orders = (Orders) o;
        return Objects.equals(getOrderId(), orders.getOrderId()) &&
                Objects.equals(getCustomers(), orders.getCustomers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getCustomers());
    }
}
