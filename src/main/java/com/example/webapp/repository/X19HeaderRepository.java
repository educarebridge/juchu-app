package com.example.webapp.repository;

import java.time.LocalDate; // 型に合わせて設定してください（Stringの場合はStringのままで可）
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.X19Header;


@Repository
public interface X19HeaderRepository extends JpaRepository<X19Header, Integer> {

    /**
     * x19_headerwork から x19_header へ追加登録
     */
    @Modifying
    @Query(value = """
        INSERT INTO x19_header (
            denpyo_no,
            juchu_date,
            seizo_date,
            nouhin_date,
            tokui_no,
            tokui_name,
            bumon_no,
            bumon_name,
            tenpo_no,
            tenpo_name,
            tanto_no,
            tanto_name
        )
        SELECT
            denpyo_no,
            juchu_date,
            seizo_date,
            nouhin_date,
            tokui_no,
            tokui_name,
            bumon_no,
            bumon_name,
            tenpo_no,
            tenpo_name,
            tanto_no,
            tanto_name
        FROM x19_headerwork
        """, nativeQuery = true)

    int insertFromHeaderWork();

    /**
     * 受注伝票の存在チェック
     * （得意先コード、受注日、伝票番号に一致するレコードが存在するか）
     */
    boolean existsByTokuiNoAndJuchuDateAndDenpyoNo(Integer tokuiNo, LocalDate juchuDate, String denpyoNo);

    List<X19Header> findAllByOrderByDenpyoIdDesc();

    @Modifying
    @Transactional
    @Query("DELETE FROM X19Header h WHERE h.denpyoId = :denpyoId")
    int deleteByDenpyoId(@Param("denpyoId") Integer denpyoId);


}
