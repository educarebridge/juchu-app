package com.example.webapp.controller;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; // ★追加
import org.springframework.web.bind.annotation.PostMapping;

import com.example.webapp.form.CsvExportForm; // ★ご自身のFormクラスのパッケージに合わせて調整してください
import com.example.webapp.service.TokuiMasterService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CsvExportController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TokuiMasterService tokuiMasterService;

    // 1. 画面表示（URL: /csv-export）
    
    @GetMapping("/csv-export")
    public String showForm(Model model) {
        if (!model.containsAttribute("csvExportForm")) {
            model.addAttribute("csvExportForm", new CsvExportForm());
        }

        // お得意先一覧を画面に渡す
        model.addAttribute("tokuiList", tokuiMasterService.findAll());

        return "csv_export_form"; // templates/csv_export_form.html を表示
    }

    // 2. CSVダウンロード処理（URL: /csv-download）
    @PostMapping("/csv-export/download")
    public void downloadCsv(
            @ModelAttribute CsvExportForm form, // ★画面から入力された検索条件を受け取る
            HttpServletResponse response) throws Exception {

        // レスポンスヘッダー設定 (Shift_JIS / MS932)
        response.setContentType("text/csv; charset=MS932");
        response.setHeader("Content-Disposition", "attachment; filename=\"juchu_access_data.csv\"");

        // SQLのベース部分
        StringBuilder sql = new StringBuilder("""
            SELECT 
                h.denpyo_id, h.denpyo_no, h.juchu_date, h.seizo_date, h.nouhin_date,
                h.tokui_no, h.tokui_name, h.bumon_no, h.bumon_name,
                h.tenpo_no, h.tenpo_name, h.tanto_no, h.tanto_name,
                d.meisai_id, d.hinban, d.hinmei, d.kikaku,
                d.hacchu_tani, d.quantity, d.unit_name, d.unit_price, d.amount
            FROM x19_header h
            INNER JOIN x19_detail d ON h.denpyo_id = d.denpyo_id
            WHERE 1=1
        """);

        List<Object> params = new ArrayList<>();

        // 開始得意先コードが入力されている場合
        if (form.getStartTokuiNo() != null && !form.getStartTokuiNo().trim().isEmpty()) {
            sql.append(" AND h.tokui_no >= ?");
            params.add(Integer.valueOf(form.getStartTokuiNo().trim()));
        }

        // 終了得意先コードが入力されている場合
        if (form.getEndTokuiNo() != null && !form.getEndTokuiNo().trim().isEmpty()) {
            sql.append(" AND h.tokui_no <= ?");
            params.add(Integer.valueOf(form.getEndTokuiNo().trim()));
        }


        // 納品日の開始日が入力されている場合
        if (form.getStartDeliveryDate() != null) {
            sql.append(" AND h.nouhin_date >= ?");
            params.add(form.getStartDeliveryDate());
        }

        // 納品日の終了日が入力されている場合
        if (form.getEndDeliveryDate() != null) {
            sql.append(" AND h.nouhin_date <= ?");
            params.add(form.getEndDeliveryDate());
        }


        // ソート順の追加
        sql.append(" ORDER BY h.denpyo_id, d.meisai_id");

        // 条件付きSQLの実行
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString(), params.toArray());

        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "MS932"))) {
            
            // 1行目：日本語ヘッダー
            writer.println("伝票ID,伝票番号,受注日,製造日,納品日,得意先No,得意先名,部門番号,部門名,店舗番号,店名,担当番号,担当名,明細ID,品番,品名,規格,発注単位,数量,単位,単価,金額");

            // データ行出力
            for (Map<String, Object> row : rows) {
                StringBuilder sb = new StringBuilder();
                sb.append(nullToEmpty(row.get("denpyo_id"))).append(",");
                sb.append(nullToEmpty(row.get("denpyo_no"))).append(",");
                sb.append(nullToEmpty(row.get("juchu_date"))).append(",");
                sb.append(nullToEmpty(row.get("seizo_date"))).append(",");
                sb.append(nullToEmpty(row.get("nouhin_date"))).append(",");
                sb.append(nullToEmpty(row.get("tokui_no"))).append(",");
                sb.append(escapeCsv(row.get("tokui_name"))).append(",");
                sb.append(nullToEmpty(row.get("bumon_no"))).append(",");
                sb.append(escapeCsv(row.get("bumon_name"))).append(",");
                sb.append(nullToEmpty(row.get("tenpo_no"))).append(",");
                sb.append(escapeCsv(row.get("tenpo_name"))).append(",");
                sb.append(nullToEmpty(row.get("tanto_no"))).append(",");
                sb.append(escapeCsv(row.get("tanto_name"))).append(",");
                sb.append(nullToEmpty(row.get("meisai_id"))).append(",");
                sb.append(escapeCsv(row.get("hinban"))).append(",");
                sb.append(escapeCsv(row.get("hinmei"))).append(",");
                sb.append(escapeCsv(row.get("kikaku"))).append(",");
                sb.append(nullToEmpty(row.get("hacchu_tani"))).append(",");
                sb.append(nullToEmpty(row.get("quantity"))).append(",");
                sb.append(escapeCsv(row.get("unit_name"))).append(",");
                sb.append(nullToEmpty(row.get("unit_price"))).append(",");
                sb.append(nullToEmpty(row.get("amount")));

                writer.println(sb.toString());
            }
            writer.flush();
        }
    }

    private String nullToEmpty(Object val) {
        return val == null ? "" : val.toString();
    }

    private String escapeCsv(Object val) {
        if (val == null) return "";
        String str = val.toString();
        if (str.contains(",") || str.contains("\"") || str.contains("\n")) {
            str = str.replace("\"", "\"\"");
            return "\"" + str + "\"";
        }
        return str;
    }
}
