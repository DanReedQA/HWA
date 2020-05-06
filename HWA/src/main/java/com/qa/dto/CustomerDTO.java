package com.qa.dto;

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
}
