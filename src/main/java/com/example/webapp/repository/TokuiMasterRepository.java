package com.example.webapp.repository; // ※ご自身のパッケージ名に合わせてください

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webapp.entity.TokuiMaster;

@Repository
public interface TokuiMasterRepository extends JpaRepository<TokuiMaster, Integer> {
    // 全件取得等の基本機能は JpaRepository が自動提供します

    // 得意先コード昇順で全件取得するメソッドを明示的に定義
    // List<TokuiMaster> findAllByOrderByTokuiCodeAsc();

    List<TokuiMaster> findAllByOrderByTokuiNoAsc();

}
