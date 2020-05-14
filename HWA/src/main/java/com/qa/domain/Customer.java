package com.qa.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String firstName;
    private String surname;

    @ManyToOne (targetEntity = Order.class)
    private Order order;


    public Customer() {
    }

    public Customer(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getCustomerId(), customer.getCustomerId()) &&
                Objects.equals(getFirstName(), customer.getFirstName()) &&
                Objects.equals(getSurname(), customer.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getFirstName(), getSurname());
    }
}
