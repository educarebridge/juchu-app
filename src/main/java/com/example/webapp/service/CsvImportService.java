package com.example.webapp.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.webapp.entity.CsvHeaderDetailWork;
import com.example.webapp.repository.CsvHeaderDetailWorkRepository;

@Service
@Transactional // クラス全体をトランザクション管理対象とする
public class CsvImportService {

    @Autowired
    private CsvHeaderDetailWorkRepository csvHeaderDetailWorkRepository;

    public void importCsv(MultipartFile file, Integer tantoNo, String tantoName, Integer seizobiSettei) throws Exception {
        List<CsvHeaderDetailWork> denpyoList = new ArrayList<>();

        // 1. テーブルのデータを全件削除（初期化）
        csvHeaderDetailWorkRepository.deleteAllInBatch();

        // ヘッダー行スキップ用のフラグ宣言
        boolean isHeader = true;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // 空行をスキップ
                if (line.trim().isEmpty()) {
                    continue;
                }

                // ヘッダー行（1行目）をスキップ
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                // カンマ区切りで分割
                String[] data = line.split(",", -1);

                // --- データ整形（ダブルクォーテーション削除 ＆ トリム） ---
                String denpyoNo = cleanString(data[1]);
                int denpyoGyo = parseInteger(data[2]);
                String csvtokuiNo = cleanString(data[3]);
                String csvtokuiName = cleanString(data[4]);
                String bumonNo = cleanString(data[5]);
                String bumonName = cleanString(data[6]);
                String hinban2 = cleanString(data[13]);
                String csvhinmei = cleanString(data[14]);
                String csvnouhinNichi = cleanString(data[20]);
                String csvtenpoNo = cleanString(data[21]);
                String csvtenpoName = cleanString(data[22]);
                int hacchuTani = parseInteger(data[24]);

                // 1行ずつEntityオブジェクトへ変換して格納
                CsvHeaderDetailWork denpyo = new CsvHeaderDetailWork();

                denpyo.setDenpyoNo(denpyoNo);
                denpyo.setDenpyoGyo(denpyoGyo);

                if (csvtokuiNo != null && !csvtokuiNo.isEmpty()) {
                    denpyo.setCsvtokuiNo(Integer.parseInt(csvtokuiNo));
                }

                denpyo.setCsvtokuiName(csvtokuiName);

                if (bumonNo != null && !bumonNo.isEmpty()) {
                    denpyo.setBumonNo(Integer.parseInt(bumonNo));
                }

                denpyo.setBumonName(bumonName);
                denpyo.setHinban2(hinban2);
                denpyo.setCsvhinmei(csvhinmei);
                denpyo.setCsvnouhinNichi(csvnouhinNichi);
                denpyo.setCsvtenpoNo(csvtenpoNo);
                denpyo.setCsvtenpoName(csvtenpoName);
                denpyo.setHacchuTani(hacchuTani);

                // 画面（フォーム）から渡された共通の値をセット
                denpyo.setTantoNo(tantoNo);
                denpyo.setTantoName(tantoName);
                denpyo.setSeizobiSettei(seizobiSettei);

                denpyoList.add(denpyo);
            }
        }

        // 2. PostgreSQLへ一括登録
        csvHeaderDetailWorkRepository.saveAll(denpyoList);

        // 3. 一括登録の直後に日付を一括補正SQLで更新
        int updatedCount = csvHeaderDetailWorkRepository.updateDatesAfterCsvImport();
        System.out.println("CSV取込完了: " + updatedCount + " 件の日付を自動補正しました。");

        // 4. ★追加：得意先情報の自動補正を実行
        int updatedTokuiCount = csvHeaderDetailWorkRepository.updateTokuiFromGokanMaster();
        System.out.println("CSV取込完了: " + updatedTokuiCount + " 件の得意先情報を自動補正しました。");

        // 5. ★商品マスタからの変換更新および数量計算（今回追加）
        int updatedShohinCount = csvHeaderDetailWorkRepository.updateShohinInfo();
        System.out.println("CSV取込完了: " + updatedShohinCount + " 件の商品情報・数量を自動補正しました。");

        // 6. ★今回追加：店舗マスタからの店舗番号・店舗名自動補正を実行
        int updatedTenpoCount = csvHeaderDetailWorkRepository.updateTenpoFromMaster();
        System.out.println("CSV取込完了: " + updatedTenpoCount + " 件の店舗情報を自動補正しました。");


    }

    /**
     * 文字列からダブルクォーテーションを除去してトリムするヘルパーメソッド
     */
    private String cleanString(String val) {
        if (val == null) return "";
        return val.replace("\"", "").trim();
    }

    /**
     * 数値文字列を安全に int へ変換するヘルパーメソッド
     */
    private int parseInteger(String val) {
        String cleaned = cleanString(val);
        if (cleaned.isEmpty()) {
            return 0;
        }
        return (int) Double.parseDouble(cleaned);
    }
}
