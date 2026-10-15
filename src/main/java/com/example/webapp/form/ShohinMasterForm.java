package com.example.webapp.form;

public class ShohinMasterForm {

    // 得意先番号（主キーの一部）
    private Integer tokuiNo;

    // 得意先名
    private String tokuiName;

    // 品番（主キーの一部）
    private String hinban;

    // 品番2
    private String hinban2;

    // 品名
    private String hinmei;

    // 単価
    private Integer unitPrice;

    // 単位
    private String unitName;

    // 規格
    private String kikaku;

    // コンテナグループ
    private Integer containerGroup;

    // ピッキング入数
    private Integer pickingIrisu;

    // --- Getter / Setter ---

    public Integer getTokuiNo() {
        return tokuiNo;
    }

    public void setTokuiNo(Integer tokuiNo) {
        this.tokuiNo = tokuiNo;
    }

    public String getTokuiName() {
        return tokuiName;
    }

    public void setTokuiName(String tokuiName) {
        this.tokuiName = tokuiName;
    }

    public String getHinban() {
        return hinban;
    }

    public void setHinban(String hinban) {
        this.hinban = hinban;
    }

    public String getHinban2() {
        return hinban2;
    }

    public void setHinban2(String hinban2) {
        this.hinban2 = hinban2;
    }

    public String getHinmei() {
        return hinmei;
    }

    public void setHinmei(String hinmei) {
        this.hinmei = hinmei;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getKikaku() {
        return kikaku;
    }

    public void setKikaku(String kikaku) {
        this.kikaku = kikaku;
    }

    public Integer getContainerGroup() {
        return containerGroup;
    }

    public void setContainerGroup(Integer containerGroup) {
        this.containerGroup = containerGroup;
    }

    public Integer getPickingIrisu() {
        return pickingIrisu;
    }

    public void setPickingIrisu(Integer pickingIrisu) {
        this.pickingIrisu = pickingIrisu;
    }
}
