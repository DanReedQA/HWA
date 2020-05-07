package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Orderline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Long cardId;

    public Orderline() {

    }

    public Orderline(Long orderId, Long cardId) {
        this.orderId = orderId;
        this.cardId = cardId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orderline)) return false;
        Orderline orderline = (Orderline) o;
        return Objects.equals(getOrderId(), orderline.getOrderId()) &&
                Objects.equals(getCardId(), orderline.getCardId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getCardId());
    }
}
