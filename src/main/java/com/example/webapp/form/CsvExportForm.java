package com.example.webapp.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class CsvExportForm {

    private String startTokuiNo;
    private String startTokuiName;
    private String endTokuiNo;
    private String endTokuiName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDeliveryDate; // nouhin_date 検索用（開始）

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDeliveryDate;   // nouhin_date 検索用（終了）

    // Getter & Setter
    public String getStartTokuiNo() { return startTokuiNo; }
    public void setStartTokuiNo(String startTokuiNo) { this.startTokuiNo = startTokuiNo; }

    public String getStartTokuiName() { return startTokuiName; }
    public void setStartTokuiName(String startTokuiName) { this.startTokuiName = startTokuiName; }

    public String getEndTokuiNo() { return endTokuiNo; }
    public void setEndTokuiNo(String endTokuiNo) { this.endTokuiNo = endTokuiNo; }

    public String getEndTokuiName() { return endTokuiName; }
    public void setEndTokuiName(String endTokuiName) { this.endTokuiName = endTokuiName; }

    public LocalDate getStartDeliveryDate() { return startDeliveryDate; }
    public void setStartDeliveryDate(LocalDate startDeliveryDate) { this.startDeliveryDate = startDeliveryDate; }

    public LocalDate getEndDeliveryDate() { return endDeliveryDate; }
    public void setEndDeliveryDate(LocalDate endDeliveryDate) { this.endDeliveryDate = endDeliveryDate; }
}
