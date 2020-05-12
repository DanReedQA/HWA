package com.qa.dto;

public class OrderlineDTO {

    private Long orderId;
    private Long cardId;

    public void Orderline() {
    }

    public void Orderline(Long orderId, Long cardId) {
        this.orderId = orderId;
        this.cardId = cardId;
    }

    public Long getOrderId(Long orderId) { return orderId; }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) { this.cardId = cardId;
    }
}
