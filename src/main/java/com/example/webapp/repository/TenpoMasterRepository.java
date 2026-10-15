package com.example.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webapp.entity.TenpoMaster;

@Repository
public interface TenpoMasterRepository extends JpaRepository<TenpoMaster, Integer> {

    // 1. 店舗マスタ一覧表示用：全件を得意先コード昇順・店舗番号昇順で取得
    List<TenpoMaster> findAllByOrderByTokuiNoAscTenpoNoAsc();

    // 2. 受注伝票用（非同期API用）：特定得意先コードに紐づく店舗一覧を取得（店舗番号昇順）
    List<TenpoMaster> findByTokuiNoOrderByTenpoNoAsc(String tokuiNo);

    // 得意先コードで絞り込むメソッド（プロパティ名に合わせて命名）
    List<TenpoMaster> findByTokuiNo(Integer tokuiNo);


}
