package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boxId;
//  private Long cardId;  < --  To be added later
    private String boxName;
//  private Long totalValue;  < --  To be added later
//  private Long totalStock;  < --  To be added later


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Box)) return false;
        Box box = (Box) o;
        return Objects.equals(getBoxId(), box.getBoxId()) &&
                Objects.equals(getBoxName(), box.getBoxName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoxId(), getBoxName());
    }
}
