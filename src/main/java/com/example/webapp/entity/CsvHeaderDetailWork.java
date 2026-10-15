package com.example.webapp.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "x19_csvheaderdetal")
@Data
public class CsvHeaderDetailWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 自動採番の主キー

    // --- CSV由来 ---
    @Column(name = "denpyo_no")
    private String denpyoNo;        // 取引先伝票番号

    @Column(name = "denpyo_gyo")
    private Integer denpyoGyo;      // 伝票行番号

    @Column(name = "csvnouhin_nichi")
    private String csvnouhinNichi;  // 取引先希望納品日 (文字列)

    @Column(name = "csvtokui_no")
    private Integer csvtokuiNo;     // CSV得意先No

    @Column(name = "csvtokui_name")
    private String csvtokuiName;    // CSV得意先名

    @Column(name = "bumon_no")
    private Integer bumonNo;        // 部門番号

    @Column(name = "bumon_name")
    private String bumonName;       // 部門名

    @Column(name = "csvtenpo_no")
    private String csvtenpoNo;      // CSV店舗番号

    @Column(name = "csvtenpo_name")
    private String csvtenpoName;    // CSV店舗名

    @Column(name = "hinban2")
    private String hinban2;         // CSV品番(JANなど)

    @Column(name = "csvhinmei")
    private String csvhinmei;       // CSV品名

    @Column(name = "hacchu_tani")
    private Integer hacchuTani;     // 発注単位

    // --- 画面(HTMLフォーム)由来 ---
    @Column(name = "seizobi_settei")
    private Integer seizobiSettei;  // 製造日設定

    @Column(name = "tanto_no")
    private Integer tantoNo;        // 担当者No

    @Column(name = "tanto_name")
    private String tantoName;       // 担当者名

    // --- 内部計算・マスタ変換後に埋めるカラム ---
    @Column(name = "denpyo_id")
    private Integer denpyoId;

    @Column(name = "juchu_date")
    private LocalDate juchuDate;

    @Column(name = "seizo_date")
    private LocalDate seizoDate;

    @Column(name = "nouhin_date")
    private LocalDate nouhinDate;

    @Column(name = "tokui_kensaku")
    private String tokuiKensaku;

    @Column(name = "tokui_no")
    private Integer tokuiNo;        // 弊社得意先No

    @Column(name = "tokui_name")
    private String tokuiName;       // 弊社得意先名

    @Column(name = "tenpo_no")
    private Integer tenpoNo;        // 弊社店舗No

    @Column(name = "tenpo_name")
    private String tenpoName;       // 弊社店舗名

    @Column(name = "denpyo_id2")
    private Integer denpyoId2;

    @Column(name = "hinban")
    private String hinban;          // 弊社品番

    @Column(name = "hinmei")
    private String hinmei;          // 弊社品名

    @Column(name = "kikaku")
    private String kikaku;

    @Column(name = "quantity")
    private Integer quantity;       // 数量 (入数 × 発注単位)

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "unit_price")
    private Integer unitPrice;      // 単価

    @Column(name = "amount")
    private Integer amount;         // 金額 (数量 × 単価)

    // DBのカラムではない一時保持領域の場合、@Transient を付与する
    @Transient
    private Integer suryokei;

    @Transient
    private Long kingakukei;

    @Transient
    private List<CsvHeaderDetailWork> details;


    // --- 既存コードとの互換用ゲッター ---
    public Integer getSuryo() {
        return this.quantity != null ? this.quantity : 0;
    }

    public Integer getKingaku() {
        return this.amount != null ? this.amount : 0;
    }



}
