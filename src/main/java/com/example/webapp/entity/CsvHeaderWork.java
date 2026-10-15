package com.example.webapp.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// ※Spring Boot 2.x以前（古い環境）の場合は jakarta.persistence.* ではなく
// javax.persistence.* を使用します

@Entity
@Table(name = "x19_headerwork")
public class CsvHeaderWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "denpyo_id")
    private Long denpyoId;

    @Column(name = "denpyo_no")
    private String denpyoNo;

    @Column(name = "juchu_date")
    private LocalDate juchuDate;

    @Column(name = "seizo_date")
    private LocalDate seizoDate;

    @Column(name = "nouhin_date")
    private LocalDate nouhinDate;

    @Column(name = "tokui_no")
    private Integer tokuiNo;

    @Column(name = "tokui_name")
    private String tokuiName;

    @Column(name = "bumon_no")
    private Integer bumonNo;

    @Column(name = "bumon_name")
    private String bumonName;

    @Column(name = "tenpo_no")
    private Integer tenpoNo;

    @Column(name = "tenpo_name")
    private String tenpoName;

    @Column(name = "tanto_no")
    private Integer tantoNo;

    @Column(name = "tanto_name")
    private String tantoName;

	public Long getDenpyoId() {
		return denpyoId;
	}

	public void setDenpyoId(Long denpyoId) {
		this.denpyoId = denpyoId;
	}

	public String getDenpyoNo() {
		return denpyoNo;
	}

	public void setDenpyoNo(String denpyoNo) {
		this.denpyoNo = denpyoNo;
	}

	public LocalDate getJuchuDate() {
		return juchuDate;
	}

	public void setJuchuDate(LocalDate juchuDate) {
		this.juchuDate = juchuDate;
	}

	public LocalDate getSeizoDate() {
		return seizoDate;
	}

	public void setSeizoDate(LocalDate seizoDate) {
		this.seizoDate = seizoDate;
	}

	public LocalDate getNouhinDate() {
		return nouhinDate;
	}

	public void setNouhinDate(LocalDate nouhinDate) {
		this.nouhinDate = nouhinDate;
	}

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

	public Integer getTenpoNo() {
		return tenpoNo;
	}

	public void setTenpoNo(Integer tenpoNo) {
		this.tenpoNo = tenpoNo;
	}

	public String getTenpoName() {
		return tenpoName;
	}

	public void setTenpoName(String tenpoName) {
		this.tenpoName = tenpoName;
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

    // ゲッター・セッター（必要に応じて作成）

}
