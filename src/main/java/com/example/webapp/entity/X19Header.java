package com.example.webapp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "x19_header")
public class X19Header {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "denpyo_id")
    private Integer denpyoId;

    @Column(name = "denpyo_no", length = 20)
    private String denpyoNo;

    @Column(name = "juchu_date")
    private LocalDate juchuDate;

    @Column(name = "seizo_date")
    private LocalDate seizoDate;

    @Column(name = "nouhin_date")
    private LocalDate nouhinDate;

    @Column(name = "tokui_kensaku", length = 100)
    private String tokuiKensaku;

    @Column(name = "tokui_no")
    private Integer tokuiNo;

    @Column(name = "tokui_name", length = 100)
    private String tokuiName;

    @Column(name = "bumon_no")
    private Integer bumonNo;

    @Column(name = "bumon_name", length = 50)
    private String bumonName;

    @Column(name = "tenpo_no")
    private Integer tenpoNo;

    @Column(name = "tenpo_name", length = 100)
    private String tenpoName;

    @Column(name = "tanto_no")
    private Integer tantoNo;

    @Column(name = "tanto_name", length = 50)
    private String tantoName;

    @Column(name = "tekiyo", length = 100)
    private String tekiyo;

    @OneToMany(mappedBy = "header", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<X19Detail> details = new ArrayList<>();

    // ヘッダーと明細の双方向関連付け用ヘルパーメソッド
    public void addDetail(X19Detail detail) {
        details.add(detail);
        detail.setHeader(this);
    }

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

    public List<X19Detail> getDetails() { return details; }
    public void setDetails(List<X19Detail> details) { this.details = details; }
}
