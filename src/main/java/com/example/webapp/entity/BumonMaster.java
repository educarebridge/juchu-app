package com.example.webapp.entity; // パッケージ名は既存のプロジェクトに合わせてください

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bumon_master")
public class BumonMaster {

    @Id
    @Column(name = "bumon_no")
    private Integer bumonNo;

    @Column(name = "bumon_name", length = 100)
    private String bumonName;

    // デフォルトコンストラクタ
    public BumonMaster() {
    }

    public BumonMaster(Integer bumonNo, String bumonName) {
        this.bumonNo = bumonNo;
        this.bumonName = bumonName;
    }

    // ゲッター・セッター
    public Integer getBumonNo() {
        return bumonNo;
    }

    public void setBumonNo(Integer bumonNo) {
        this.bumonNo = bumonNo;
    }

    public String getBumonName() {
        return bumonName;
    }

    public void setBumonName(String bumonName) {
        this.bumonName = bumonName;
    }
}
