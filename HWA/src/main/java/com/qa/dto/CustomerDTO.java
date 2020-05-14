package com.qa.dto;

import java.util.Objects;

public class CustomerDTO {

    private Long customerId;
    private String firstName;
    private String surname;

    public CustomerDTO() {
    }

    public CustomerDTO(String firstName, String surname) {
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
    public String toString() {
        return "CustomerDTO{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO)) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getSurname(), that.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getFirstName(), getSurname());
    }
}
