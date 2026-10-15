package com.example.webapp.entity; // ※ご自身のパッケージ名に合わせてください

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tokui_master")
@Data
public class TokuiMaster {

    @Id
    @Column(name = "tokui_no")
    private Integer tokuiNo;

    @Column(name = "tokui_name")
    private String tokuiName;
}
