package com.example.webapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "shohin_master")
@IdClass(ShohinMasterId.class)
public class ShohinMaster {

    @Id
    @Column(name = "tokui_no")
    private Integer tokuiNo;

    @Column(name = "tokui_name", length = 100)
    private String tokuiName;

    @Id
    @Column(name = "hinban", length = 50)
    private String hinban;

    @Column(name = "hinban2", length = 50)
    private String hinban2;

    @Column(name = "hinmei", length = 100)
    private String hinmei;

    @Column(name = "unit_price")
    private Integer unitPrice;

    @Column(name = "unit_name", length = 20)
    private String unitName;

    @Column(name = "kikaku", length = 50)
    private String kikaku;

    @Column(name = "container_group", length = 20)
    private Integer containerGroup;

    @Column(name = "picking_irisu", length = 20)
    private Integer pickingIrisu;

    // --- Getter / Setter ---
    public Integer getTokuiNo() { return tokuiNo; }
    public void setTokuiNo(Integer tokuiNo) { this.tokuiNo = tokuiNo; }

    public String getTokuiName() { return tokuiName; }
    public void setTokuiName(String tokuiName) { this.tokuiName = tokuiName; }

    public String getHinban() { return hinban; }
    public void setHinban(String hinban) { this.hinban = hinban; }

    public String getHinban2() { return hinban2; }
    public void setHinban2(String hinban2) { this.hinban2 = hinban2; }

    public String getHinmei() { return hinmei; }
    public void setHinmei(String hinmei) { this.hinmei = hinmei; }

    public Integer getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Integer unitPrice) { this.unitPrice = unitPrice; }

    public String getUnitName() { return unitName; }
    public void setUnitName(String unitName) { this.unitName = unitName; }

    public String getKikaku() { return kikaku; }
    public void setKikaku(String kikaku) { this.kikaku = kikaku; }

    public Integer getContainerGroup() { return containerGroup; }
    public void setContainerGroup(Integer containerGroup) { this.containerGroup = containerGroup; }

    public Integer getPickingIrisu() { return pickingIrisu; }
    public void setPickingIrisu(Integer pickingIrisu) { this.pickingIrisu = pickingIrisu; }
}
