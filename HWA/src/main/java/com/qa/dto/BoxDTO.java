package com.qa.dto;

import com.qa.domain.Card;
import java.util.ArrayList;
import java.util.List;

public class BoxDTO {

    private Long boxId;
    //  private Long cardId;  < --  To be added later
    private String boxName;
//  private Long totalValue;  < --  To be added later
//  private Long totalStock;  < --  To be added later
    private List<CardDTO> cards;

    public BoxDTO() {
    }

    public BoxDTO(String boxName, List<CardDTO> cards) {
        super();
        this.boxName = boxName;
        this.cards = cards;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public List<CardDTO> getCards() {
        return cards;
    }

    public void setCards(List<CardDTO> cards) {
        this.cards = cards;
    }
}
