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
    private Long cardId;
    private String cardName;
    private String rarity;
    private Long stock;
    private Long value;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long id) {
        this.cardId = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String title) {
        this.cardName = title;
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
        return getCardId().equals(card.getCardId()) &&
                getCardName().equals(card.getCardName()) &&
                getRarity().equals(card.getRarity()) &&
                getStock().equals(card.getStock()) &&
                getValue().equals(card.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardId(), getCardName(), getRarity(), getStock(), getValue());
    }
}
