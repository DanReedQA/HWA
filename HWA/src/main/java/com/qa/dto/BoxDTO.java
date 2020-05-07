package com.qa.dto;

import com.qa.domain.Card;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.List;

@Ignore
public class BoxDTO {

    private Long boxId;
    private String boxName;

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
