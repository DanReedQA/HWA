package com.qa.dto;

import java.util.Objects;

public class CardDTO {
    private Long cardId;
    private String cardName;
    private Long value;

    public CardDTO() {
    }

    public CardDTO(String cardName, Long value){
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
        this.cardName = cardName;
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
                Objects.equals(getValue(), cardDTO.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardId(), getCardName(), getValue());
    }
}
