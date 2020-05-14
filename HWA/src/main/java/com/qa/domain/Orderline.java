package com.qa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Orderline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Long cardId;

    @OneToMany(mappedBy = "orderline", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "orderline", fetch = FetchType.LAZY)
    private List<Card> Cards = new ArrayList<>();

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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Card> getCards() {
        return Cards;
    }

    public void setCards(List<Card> cards) {
        Cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orderline)) return false;
        Orderline orderline = (Orderline) o;
        return Objects.equals(getOrderId(), orderline.getOrderId()) &&
                Objects.equals(getCardId(), orderline.getCardId()) &&
                Objects.equals(getOrders(), orderline.getOrders()) &&
                Objects.equals(getCards(), orderline.getCards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getCardId(), getOrders(), getCards());
    }
}
