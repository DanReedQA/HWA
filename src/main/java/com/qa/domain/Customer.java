package com.qa.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String username;

    @ManyToOne (targetEntity = Orders.class)
    private Orders orders;


    public Customer() {
    }

    public Customer(String username) {
        super();
        this.username = username;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String firstName) {
        this.username = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getCustomerId(), customer.getCustomerId()) &&
                Objects.equals(getUsername(), customer.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getUsername());
    }
}
