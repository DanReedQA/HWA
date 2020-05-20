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
    private Long value;

    @ManyToOne (targetEntity = Box.class)
    private Box box;

    public Card() {
    }

    public Card(String cardName, Long value) {
        this.cardName = cardName;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return Objects.equals(getCardId(), card.getCardId()) &&
                Objects.equals(getCardName(), card.getCardName()) &&
                Objects.equals(getValue(), card.getValue()) &&
                Objects.equals(getBox(), card.getBox());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardId(), getCardName(), getValue(), getBox());
    }
}
