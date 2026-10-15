package com.example.webapp.repository; // パッケージ名は既存に合わせてください

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webapp.entity.BumonMaster;

@Repository
public interface BumonMasterRepository extends JpaRepository<BumonMaster, Integer> {
    // 部門番号順に全件取得
    List<BumonMaster> findAllByOrderByBumonNoAsc();
}
