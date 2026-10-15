package com.example.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.X19Detail;

@Repository
public interface X19DetailRepository extends JpaRepository<X19Detail, Integer> {

    @Modifying
    @Query(value = """
        INSERT INTO x19_detail (
            denpyo_id,
            hinban_kensaku,
            hinban,
            hinban2,
            hinban3,
            hinmei,
            kikaku,
            hacchu_tani,
            quantity,
            unit_name,
            unit_price,
            amount
        )
        SELECT
            h.denpyo_id,
            dw.hinban_kensaku,
            dw.hinban,
            dw.hinban2,
            dw.hinban3,
            dw.hinmei,
            dw.kikaku,
            dw.hacchu_tani,
            dw.quantity,
            dw.unit_name,
            dw.unit_price,
            dw.amount
        FROM x19_detailwork dw
        JOIN x19_headerwork hw
            ON dw.denpyo_id = hw.denpyo_id
        JOIN x19_header h
            ON h.denpyo_no = hw.denpyo_no
        """, nativeQuery = true)
    int insertFromDetailWork();


    /**
     * x19_detail から伝票IDに対応する明細を取得
     */
    List<X19Detail> findByHeader_DenpyoIdOrderByMeisaiId(Integer denpyoId);

    @Modifying
    @Transactional
    @Query("DELETE FROM X19Detail d WHERE d.header.denpyoId = :denpyoId")
    int deleteByDenpyoId(@Param("denpyoId") Integer denpyoId);

}
