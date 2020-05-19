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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO)) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getUsername());
    }
}
