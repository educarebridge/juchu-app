package com.example.webapp.entity;

import java.io.Serializable;
import java.util.Objects;

public class ShohinMasterId implements Serializable {

    private Integer tokuiNo;
    private String hinban;

    public ShohinMasterId() {}

    public ShohinMasterId(Integer tokuiNo, String hinban) {
        this.tokuiNo = tokuiNo;
        this.hinban = hinban;
    }

    public Integer getTokuiNo() { return tokuiNo; }
    public void setTokuiNo(Integer tokuiNo) { this.tokuiNo = tokuiNo; }

    public String getHinban() { return hinban; }
    public void setHinban(String hinban) { this.hinban = hinban; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShohinMasterId that = (ShohinMasterId) o;
        return Objects.equals(tokuiNo, that.tokuiNo) && Objects.equals(hinban, that.hinban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokuiNo, hinban);
    }
}
