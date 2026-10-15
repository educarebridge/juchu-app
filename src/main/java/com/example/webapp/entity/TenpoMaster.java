package com.example.webapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tenpo_master")
@Data
public class TenpoMaster {

    @Id
    @Column(name = "tenpo_no")
    private Integer tenpoNo; // 店舗コード/番号

    @Column(name = "tokui_no")
    private Integer tokuiNo; // 得意先番号

    @Column(name = "tenpo_name")
    private String tenpoName; // 店舗名
}
