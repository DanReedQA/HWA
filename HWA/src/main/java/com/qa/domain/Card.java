package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardId;
    private String cardName;
    private String rarity;
    private Long stock;
    private Long value;

    @ManyToOne (targetEntity = Box.class)
    private Box box;

    @ManyToOne (targetEntity = Orderline.class)
    private Orderline orderline;

    public Card() {
    }

    public Card(String cardName, String rarity, Long stock, Long value) {
        this.cardName = cardName;
        this.rarity = rarity;
        this.stock = stock;
        this.value = value;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName; }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity; }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock; }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value; }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Orderline getOrderline() {
        return orderline;
    }

    public void setOrderline(Orderline orderline) {
        this.orderline = orderline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return Objects.equals(getCardId(), card.getCardId()) &&
                Objects.equals(getCardName(), card.getCardName()) &&
                Objects.equals(getRarity(), card.getRarity()) &&
                Objects.equals(getStock(), card.getStock()) &&
                Objects.equals(getValue(), card.getValue()) &&
                Objects.equals(getBox(), card.getBox()) &&
                Objects.equals(getOrderline(), card.getOrderline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardId(), getCardName(), getRarity(), getStock(), getValue(), getBox(), getOrderline());
    }
}
