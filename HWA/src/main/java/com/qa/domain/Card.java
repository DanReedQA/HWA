package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long card_id;
    private String card_name;
    private String rarity;
    private Long stock;
    private Long value;

    public Long getCard_id() {
        return card_id;
    }

    public void setCard_id(Long id) {
        this.card_id = id;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String title) {
        this.card_name = title;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String description) {
        this.rarity = description;
    }

    public Long getStock() { return stock; }

    public void setStock(Long stock) { this.stock = stock; }

    public Long getValue() { return value; }

    public void setValue(Long value) { this.value = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getCard_id().equals(card.getCard_id()) &&
                getCard_name().equals(card.getCard_name()) &&
                getRarity().equals(card.getRarity()) &&
                getStock().equals(card.getStock()) &&
                getValue().equals(card.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCard_id(), getCard_name(), getRarity(), getStock(), getValue());
    }
}
