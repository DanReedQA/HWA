package com.qa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

@Entity
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boxId;
//  private Long cardId;  < --  To be added later
    private String boxName;
//  private Long totalValue;  < --  To be added later
//  private Long totalStock;  < --  To be added later

    @OneToMany (mappedBy = "box", fetch = FetchType.LAZY)
    private List<Card> cards = new ArrayList<>();

    public Box() {

    }

    public Box(String boxName) {
        this.boxName = boxName;
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

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Box)) return false;
        Box box = (Box) o;
        return Objects.equals(getBoxId(), box.getBoxId()) &&
                Objects.equals(getBoxName(), box.getBoxName()) &&
                Objects.equals(getCards(), box.getCards());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoxId(), getBoxName(), getCards());
    }
}
