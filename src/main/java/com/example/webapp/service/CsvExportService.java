package com.example.webapp.service;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.webapp.entity.X19Header;
import com.example.webapp.repository.CsvExportX19HeaderRepository;

@Service
public class CsvExportService {

    private final CsvExportX19HeaderRepository headerRepository;

    public CsvExportService(CsvExportX19HeaderRepository headerRepository) {
        this.headerRepository = headerRepository;
    }

    /**
     * 得意先コード範囲および日付範囲を指定してCSVデータを出力します。
     */
    public void writeCsv(PrintWriter writer, Integer startTokuiCode, Integer endTokuiCode, LocalDate startDate, LocalDate endDate) {
        
        // 1. 得意先コードと日付の範囲で検索データを取得
        // ※ リポジトリ側に該当メソッドを定義する必要があります
        List<X19Header> headerList = headerRepository.findByJuchuDateBetweenAndTokuiNoBetween(
                startDate, endDate, startTokuiCode, endTokuiCode);

        // 2. UTF-8 BOM の書き込み（PrintWriterの親となるOutputStreamに対して行うか、文字コード指定で対応）
        // PrintWriter経由で直接出力する場合のヘッダーおよびデータ行の出力：
        
        // ヘッダー行の書き出し
        writer.println("伝票ID,伝票番号,受注日,得意先コード,得意先名,担当者名,摘要");

        // データ行の書き出し
        for (X19Header header : headerList) {
            writer.printf("%d,\"%s\",\"%s\",%d,\"%s\",\"%s\",\"%s\"%n",
                    header.getDenpyoId(),
                    header.getDenpyoNo() != null ? header.getDenpyoNo() : "",
                    header.getJuchuDate() != null ? header.getJuchuDate() : "",
                    header.getTokuiNo() != null ? header.getTokuiNo() : 0,
                    header.getTokuiName() != null ? header.getTokuiName() : "",
                    header.getTantoName() != null ? header.getTantoName() : "",
                    header.getTekiyo() != null ? header.getTekiyo() : ""
            );
        }
        writer.flush();
    }
}
