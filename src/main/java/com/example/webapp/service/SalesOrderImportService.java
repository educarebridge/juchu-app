package com.example.webapp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

// 画面表示用のEntity/DTOクラスをインポート（パッケージ名が異なる場合は環境に合わせて変更してください）
import com.example.webapp.entity.CsvHeaderDetailWork;

@Service
public class SalesOrderImportService {

    // データベース接続情報
    private static final String URL = "jdbc:postgresql://localhost:5432/JuchuLife";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    /**
     * CSV取り込み後にヘッダーと明細に自動振り分け・登録を行うメソッド
     */
    public void processCsvToWorkTables() {

        // 1. ワークテーブルの全削除SQL（外部キー制約に合わせて Detail -> Header の順）
        String sqlDeleteDetail = "DELETE FROM x19_detailwork;";
        String sqlDeleteHeader = "DELETE FROM x19_headerwork;";

        // 2. ヘッダー登録SQL
        String sqlInsertHeader = 
            "INSERT INTO x19_headerwork (" +
            "    denpyo_no, juchu_date, seizo_date, nouhin_date, " +
            "    tokui_no, tokui_name, bumon_no, bumon_name, " +
            "    tenpo_no, tenpo_name, tanto_no, tanto_name" +
            ") " +
            "SELECT DISTINCT " +
            "    denpyo_no, juchu_date, seizo_date, nouhin_date, " +
            "    tokui_no, tokui_name, bumon_no, bumon_name, " +
            "    tenpo_no, tenpo_name, tanto_no, tanto_name " +
            "FROM x19_csvheaderdetal " +
            "ON CONFLICT (denpyo_no) DO NOTHING;";

        // 3. 明細登録SQL
        String sqlInsertDetail = 
            "INSERT INTO x19_detailwork (" +
            "    denpyo_id, hinban, hinban2, hinmei, kikaku, " +
            "    hacchu_tani, quantity, unit_name, unit_price, amount" +
            ") " +
            "SELECT " +
            "    h.denpyo_id, c.hinban, c.hinban2, c.hinmei, c.kikaku, " +
            "    c.hacchu_tani, c.quantity, c.unit_name, c.unit_price, c.amount " +
            "FROM x19_csvheaderdetal c " +
            "JOIN x19_headerwork h ON c.denpyo_no = h.denpyo_no;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false); // トランザクション開始

            try (PreparedStatement stmtDelDetail = conn.prepareStatement(sqlDeleteDetail);
                 PreparedStatement stmtDelHeader = conn.prepareStatement(sqlDeleteHeader);
                 PreparedStatement stmtHeader = conn.prepareStatement(sqlInsertHeader);
                 PreparedStatement stmtDetail = conn.prepareStatement(sqlInsertDetail)) {

                // ★ 1. まず既存のワークデータを全削除
                stmtDelDetail.executeUpdate();
                stmtDelHeader.executeUpdate();

                // ★ 2. ヘッダーと明細の振り分け登録
                stmtHeader.executeUpdate();
                stmtDetail.executeUpdate();

                conn.commit(); // すべて成功したらコミット
                System.out.println("データクリアおよび変換・登録処理が正常に完了しました。");

            } catch (SQLException e) {
                conn.rollback(); // エラー時はロールバック
                e.printStackTrace();
                throw new RuntimeException("DB登録中にエラーが発生しました", e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DB接続に失敗しました", e);
        }
    }

    /**
     * 画面表示用にワークデータの一覧を取得するメソッド
     */
    public List<CsvHeaderDetailWork> getWorkDataList() {
        List<CsvHeaderDetailWork> list = new ArrayList<>();
        
        // 元のCSVワークテーブル全件取得SQL（必要に応じて並び順を指定）
        String sql = "SELECT * FROM x19_csvheaderdetal ORDER BY id;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CsvHeaderDetailWork item = new CsvHeaderDetailWork();
                

                // DBの値をオブジェクトにセット
                item.setId(rs.getLong("id"));
                item.setDenpyoNo(rs.getString("denpyo_no"));

                // --- 日付項目 (LocalDate型) ---
                item.setJuchuDate(rs.getObject("juchu_date", LocalDate.class));
                item.setSeizoDate(rs.getObject("seizo_date", LocalDate.class));
                item.setNouhinDate(rs.getObject("nouhin_date", LocalDate.class));

                // --- 数値項目 (Integer型) ---
                item.setTokuiNo(rs.getObject("tokui_no", Integer.class));
                item.setTokuiName(rs.getString("tokui_name"));
                item.setBumonNo(rs.getObject("bumon_no", Integer.class));
                item.setBumonName(rs.getString("bumon_name"));
                item.setTenpoNo(rs.getObject("tenpo_no", Integer.class));
                item.setTenpoName(rs.getString("tenpo_name"));
                item.setTantoNo(rs.getObject("tanto_no", Integer.class));
                item.setTantoName(rs.getString("tanto_name"));

                // --- 商品・金額項目 ---
                item.setHinban(rs.getString("hinban"));
                item.setHinban2(rs.getString("hinban2"));
                item.setHinmei(rs.getString("hinmei"));
                item.setKikaku(rs.getString("kikaku"));
                item.setHacchuTani(rs.getObject("hacchu_tani", Integer.class));
                item.setQuantity(rs.getObject("quantity", Integer.class));
                item.setUnitName(rs.getString("unit_name"));
                item.setUnitPrice(rs.getObject("unit_price", Integer.class));
                item.setAmount(rs.getObject("amount", Integer.class));

                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("ワークデータの取得中にエラーが発生しました", e);
        }

        return list;
    }
}
