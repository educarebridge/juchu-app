package com.example.webapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id; // ★これを追加（主キー用）
import jakarta.persistence.Table;

@Entity
@Table(name = "tanto_master")
public class Tanto {
    
    @Id // ★これを追加（この項目が主キーであることをSpringに教えます）
    private Integer tantoNo;
    
    private String tantoName;

    public Tanto() {}

    public Tanto(Integer tantoNo, String tantoName) {
        this.tantoNo = tantoNo;
        this.tantoName = tantoName;
    }

    public Integer getTantoNo() {
        return tantoNo;
    }

    public void setTantoNo(Integer tantoNo) {
        this.tantoNo = tantoNo;
    }

    public String getTantoName() {
        return tantoName;
    }

    public void setTantoName(String tantoName) {
        this.tantoName = tantoName;
    }
}
