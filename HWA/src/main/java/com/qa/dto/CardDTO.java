package com.qa.dto;

import com.qa.domain.Box;

import java.util.Objects;

public class CardDTO {
    private Long cardId;
    private String cardName;
    private String rarity;
    private Long stock;
    private Long value;

    public CardDTO() {
    }

    public CardDTO(String cardName, String rarity, Long stock, Long value){
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
        this.cardName = cardName;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardDTO)) return false;
        CardDTO cardDTO = (CardDTO) o;
        return Objects.equals(getCardId(), cardDTO.getCardId()) &&
                Objects.equals(getCardName(), cardDTO.getCardName()) &&
                Objects.equals(getRarity(), cardDTO.getRarity()) &&
                Objects.equals(getStock(), cardDTO.getStock()) &&
                Objects.equals(getValue(), cardDTO.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardId(), getCardName(), getRarity(), getStock(), getValue());
    }
}
