package com.example.webapp.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.webapp.entity.X19Detail;

@Repository
public class X19DetailWorkRepository {

    private final JdbcTemplate jdbcTemplate;

    public X19DetailWorkRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<X19Detail> findByDenpyoId(Integer denpyoId) {

        String sql = """
            SELECT
                meisai_id,
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
            FROM x19_detailwork
            WHERE denpyo_id = ?
            ORDER BY meisai_id
            """;

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> {

                X19Detail detail = new X19Detail();

                detail.setMeisaiId(rs.getInt("meisai_id"));
                detail.setHinbanKensaku(rs.getString("hinban_kensaku"));
                detail.setHinban(rs.getString("hinban"));
                detail.setHinban2(rs.getString("hinban2"));
                detail.setHinban3(rs.getString("hinban3"));
                detail.setHinmei(rs.getString("hinmei"));
                detail.setKikaku(rs.getString("kikaku"));
                detail.setHacchuTani(rs.getInt("hacchu_tani"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setUnitName(rs.getString("unit_name"));
                detail.setUnitPrice(rs.getInt("unit_price"));
                detail.setAmount(rs.getInt("amount"));

                return detail;
            },
            denpyoId
        );
    }
}
