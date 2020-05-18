package com.qa.dto;

import java.util.Objects;

public class CustomerDTO {

    private Long customerId;
    private String username;

    public CustomerDTO() {
    }

    public CustomerDTO(String firstName) {
        this.username = firstName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return username;
    }

    public void setFirstName(String firstName) {
        this.username = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO)) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getFirstName(), that.getFirstName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getFirstName());
    }
}
