package com.example.webapp.repository; // ※ご自身のパッケージ名に合わせてください

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webapp.entity.TokuiMaster;

@Repository
public interface TokuiMasterRepository extends JpaRepository<TokuiMaster, Integer> {
    // 全件取得等の基本機能は JpaRepository が自動提供します
}
