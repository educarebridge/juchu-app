package com.example.webapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "x19_detail")
public class X19Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meisai_id")
    private Integer meisaiId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "denpyo_id")
    private X19Header header;

    @Column(name = "hinban_kensaku", length = 50)
    private String hinbanKensaku;

    @Column(name = "hinban", length = 50)
    private String hinban;

    @Column(name = "hinban2", length = 50)
    private String hinban2;

    @Column(name = "hinban3", length = 50)
    private String hinban3;

    @Column(name = "hinmei", length = 100)
    private String hinmei;

    @Column(name = "kikaku", length = 50)
    private String kikaku;

    @Column(name = "hacchu_tani")
    private Integer hacchuTani;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_name", length = 20)
    private String unitName;

    @Column(name = "unit_price")
    private Integer unitPrice;

    @Column(name = "amount")
    private Integer amount;


    // --- Getter / Setter ---
    public Integer getMeisaiId() { return meisaiId; }
    public void setMeisaiId(Integer meisaiId) { this.meisaiId = meisaiId; }

    public X19Header getHeader() { return header; }
    public void setHeader(X19Header header) { this.header = header; }

    public String getHinbanKensaku() { return hinbanKensaku; }
    public void setHinbanKensaku(String hinbanKensaku) { this.hinbanKensaku = hinbanKensaku; }

    public String getHinban() { return hinban; }
    public void setHinban(String hinban) { this.hinban = hinban; }

    public String getHinban2() { return hinban2; }
    public void setHinban2(String hinban2) { this.hinban2 = hinban2; }

    public String getHinban3() { return hinban3; }
    public void setHinban3(String hinban3) { this.hinban3 = hinban3; }

    public String getHinmei() { return hinmei; }
    public void setHinmei(String hinmei) { this.hinmei = hinmei; }

    public String getKikaku() { return kikaku; }
    public void setKikaku(String kikaku) { this.kikaku = kikaku; }

    public Integer getHacchuTani() { return hacchuTani; }
    public void setHacchuTani(Integer hacchuTani) { this.hacchuTani = hacchuTani; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getUnitName() { return unitName; }
    public void setUnitName(String unitName) { this.unitName = unitName; }

    public Integer getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Integer unitPrice) { this.unitPrice = unitPrice; }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }



}
