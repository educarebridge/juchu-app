package com.example.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.webapp.entity.ShohinMaster;
import com.example.webapp.entity.ShohinMasterId;

@Repository
public interface ShohinMasterRepository extends JpaRepository<ShohinMaster, ShohinMasterId> {

    // 得意先名と品名のあいまい検索
    @Query("SELECT s FROM ShohinMaster s WHERE " +
           "(:tokuiName IS NULL OR :tokuiName = '' OR s.tokuiName LIKE %:tokuiName%) AND " +
           "(:hinmei IS NULL OR :hinmei = '' OR s.hinmei LIKE %:hinmei%)")
    List<ShohinMaster> searchShohin(
        @Param("tokuiName") String tokuiName, 
        @Param("hinmei") String hinmei
    );

    // ★ここを追加：得意先CD昇順、品番昇順で全件取得するメソッド
    List<ShohinMaster> findAllByOrderByTokuiNoAscHinbanAsc();
}
