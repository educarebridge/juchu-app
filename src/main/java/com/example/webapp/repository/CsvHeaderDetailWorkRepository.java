package com.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.CsvHeaderDetailWork;

@Repository
public interface CsvHeaderDetailWorkRepository extends JpaRepository<CsvHeaderDetailWork, Long> {

    // CSV取込後の日付自動補正（テーブル名が x19_csvheaderdetal の場合）
    @Modifying
    @Transactional
    @Query(value = "UPDATE x19_csvheaderdetal SET " +
                   "nouhin_date = to_date(csvnouhin_nichi, 'YYYYMMDD'), " +
                   "seizo_date = to_date(csvnouhin_nichi, 'YYYYMMDD') + COALESCE(seizobi_settei, 0), " +
                   "juchu_date = to_date(csvnouhin_nichi, 'YYYYMMDD') + COALESCE(seizobi_settei, 0)", 
           nativeQuery = true)
    int updateDatesAfterCsvImport();

    // ★追加：得意先互換マスターからの得意先コード・得意先名自動補正クエリ
    @Modifying
    @Transactional
    @Query(value = "UPDATE x19_csvheaderdetal WORK "
                 + "SET tokui_no = M.tokui_no, "
                 + "    tokui_name = M.tokui_name "
                 + "FROM tokuigokan_master M "
                 + "WHERE WORK.csvtokui_no = M.gokan_no", 
           nativeQuery = true)
    int updateTokuiFromGokanMaster();

    // ★追加：商品マスタ情報・数量・金額更新処理
    @Modifying
    @Transactional
    @Query(value = "UPDATE x19_csvheaderdetal WORK " +
                 "SET hinban = M.hinban, " +
                 "    hinmei = M.hinmei, " +
                 "    kikaku = M.kikaku, " +
                 "    unit_price = M.unit_price, " +
                 "    unit_name = M.unit_name, " +
                 "    quantity = CASE " +
                 "                 WHEN M.kikaku ~ '^[0-9]+$' THEN WORK.hacchu_tani * CAST(M.kikaku AS INT) " +
                 "                 ELSE WORK.hacchu_tani " +
                 "               END, " +
                 "    amount = (CASE " +
                 "                WHEN M.kikaku ~ '^[0-9]+$' THEN WORK.hacchu_tani * CAST(M.kikaku AS INT) " +
                 "                ELSE WORK.hacchu_tani " +
                 "              END) * M.unit_price " +
                 "FROM shohin_master M " +
                 "WHERE WORK.hinban2 = M.hinban2 " +
                 "  AND WORK.tokui_no = M.tokui_no", nativeQuery = true)
    int updateShohinInfo();

    // ★追加：店舗マスタからの店舗番号・店舗名自動補正クエリ
    @Modifying
    @Transactional
    @Query(value = "UPDATE x19_csvheaderdetal WORK "
                 + "SET tenpo_no = M.tenpo_no, "
                 + "    tenpo_name = M.tenpo_name "
                 + "FROM tenpo_master M "
                 + "WHERE WORK.tokui_no = M.tokui_no "
                 + "  AND WORK.csvtenpo_no IS NOT NULL "
                 + "  AND WORK.csvtenpo_no ~ '[0-9]{3}$' " // 右から3文字が数字の場合のみ対象
                 + "  AND CAST(RIGHT(WORK.csvtenpo_no, 3) AS INT) = M.tenpo_no", 
           nativeQuery = true)
    int updateTenpoFromMaster();

}
