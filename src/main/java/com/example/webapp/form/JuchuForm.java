package com.example.webapp.form; // ご自身のパッケージ名に合わせて変更してください

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class JuchuForm {

    // --- Header情報 (x19_header) ---
    private Integer denpyoId;
    private String denpyoNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate juchuDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate seizoDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nouhinDate;
    
    private String tokuiKensaku;
    private Integer tokuiNo;
    private String tokuiName;
    private Integer bumonNo;
    private String bumonName;
    private Integer tenpoNo;
    private String tenpoName;
    private Integer tantoNo;
    private String tantoName;
    private String tekiyo;

    // --- Detail情報リスト (x19_detail) ---
    private List<JuchuDetailForm> details = new ArrayList<>();

    // --- Getter / Setter ---
    public Integer getDenpyoId() { return denpyoId; }
    public void setDenpyoId(Integer denpyoId) { this.denpyoId = denpyoId; }

    public String getDenpyoNo() { return denpyoNo; }
    public void setDenpyoNo(String denpyoNo) { this.denpyoNo = denpyoNo; }

    public LocalDate getJuchuDate() { return juchuDate; }
    public void setJuchuDate(LocalDate juchuDate) { this.juchuDate = juchuDate; }

    public LocalDate getSeizoDate() { return seizoDate; }
    public void setSeizoDate(LocalDate seizoDate) { this.seizoDate = seizoDate; }

    public LocalDate getNouhinDate() { return nouhinDate; }
    public void setNouhinDate(LocalDate nouhinDate) { this.nouhinDate = nouhinDate; }

    public String getTokuiKensaku() { return tokuiKensaku; }
    public void setTokuiKensaku(String tokuiKensaku) { this.tokuiKensaku = tokuiKensaku; }

    public Integer getTokuiNo() { return tokuiNo; }
    public void setTokuiNo(Integer tokuiNo) { this.tokuiNo = tokuiNo; }

    public String getTokuiName() { return tokuiName; }
    public void setTokuiName(String tokuiName) { this.tokuiName = tokuiName; }

    public Integer getBumonNo() { return bumonNo; }
    public void setBumonNo(Integer bumonNo) { this.bumonNo = bumonNo; }

    public String getBumonName() { return bumonName; }
    public void setBumonName(String bumonName) { this.bumonName = bumonName; }

    public Integer getTenpoNo() { return tenpoNo; }
    public void setTenpoNo(Integer tenpoNo) { this.tenpoNo = tenpoNo; }

    public String getTenpoName() { return tenpoName; }
    public void setTenpoName(String tenpoName) { this.tenpoName = tenpoName; }

    public Integer getTantoNo() { return tantoNo; }
    public void setTantoNo(Integer tantoNo) { this.tantoNo = tantoNo; }

    public String getTantoName() { return tantoName; }
    public void setTantoName(String tantoName) { this.tantoName = tantoName; }

    public String getTekiyo() { return tekiyo; }
    public void setTekiyo(String tekiyo) { this.tekiyo = tekiyo; }

    public List<JuchuDetailForm> getDetails() { return details; }
    public void setDetails(List<JuchuDetailForm> details) { this.details = details; }
}
