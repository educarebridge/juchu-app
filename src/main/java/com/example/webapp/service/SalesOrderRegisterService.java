package com.example.webapp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.repository.X19DetailRepository;
import com.example.webapp.repository.X19HeaderRepository;

@Service
public class SalesOrderRegisterService {

    // データベース接続情報 (SalesOrderImportService と同じものを使用)
    private static final String URL = "jdbc:postgresql://localhost:5432/JuchuLife";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private final X19HeaderRepository x19HeaderRepository;
    private final X19DetailRepository x19DetailRepository;

    public SalesOrderRegisterService(
            X19HeaderRepository x19HeaderRepository,
            X19DetailRepository x19DetailRepository) {

        this.x19HeaderRepository = x19HeaderRepository;
        this.x19DetailRepository = x19DetailRepository;
    }

    /**
     * ワークテーブル（x19_headerwork）の件数を取得する
     */
    public int getWorkCount() {
        String sql = "SELECT COUNT(*) FROM x19_headerwork;";
        return executeCountQuery(sql);
    }

    /**
     * 重複件数（すでに本テーブル x19_header に存在する伝票番号の件数）を取得する
     */
    public int getExistCount() {
        String sql = "SELECT COUNT(*) FROM x19_headerwork w "
                   + "INNER JOIN x19_header h ON w.denpyo_no = h.denpyo_no;";
        return executeCountQuery(sql);
    }

    /**
     * 件数取得用共通メソッド
     */
    private int executeCountQuery(String sql) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("件数の取得中にエラーが発生しました", e);
        }
        return 0;
    }

    /**
     * x19_headerwork → x19_header
     * x19_detailwork → x19_detail
     *
     * ヘッダーと明細を追加登録する
     */
    @Transactional
    public int registerHeader() {

        // ① ヘッダーを登録
        int headerCount =
                x19HeaderRepository.insertFromHeaderWork();

        System.out.println(
                "x19_headerwork → x19_header 登録件数: "
                + headerCount
        );

        // ② 明細を登録
        int detailCount =
                x19DetailRepository.insertFromDetailWork();

        System.out.println(
                "x19_detailwork → x19_detail 登録件数: "
                + detailCount
        );

        return headerCount;
    }
}
