package com.example.webapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webapp.entity.ShohinMaster;
import com.example.webapp.entity.ShohinMasterId;
import com.example.webapp.repository.ShohinMasterRepository;

@Service
public class ShohinMasterService {

    @Autowired
    private ShohinMasterRepository repository;

    // 検索処理（条件指定あり/未入力で自動分岐）
    public List<ShohinMaster> searchShohin(String tokuiName, String hinmei) {
        if ((tokuiName == null || tokuiName.isEmpty()) && (hinmei == null || hinmei.isEmpty())) {
            // 検索キーがない場合は昇順ソートした全件を返す
            return repository.findAllByOrderByTokuiNoAscHinbanAsc();
        }
        return repository.searchShohin(tokuiName, hinmei);
    }

    // 全件取得（昇順ソート付き）
    public List<ShohinMaster> findAll() {
        return repository.findAllByOrderByTokuiNoAscHinbanAsc();
    }

    public Optional<ShohinMaster> findById(Integer tokuiNo, String hinban) {
        return repository.findById(new ShohinMasterId(tokuiNo, hinban));
    }

    public void save(ShohinMaster shohinMaster) {
        repository.save(shohinMaster);
    }

    // 削除処理
    public void deleteById(Integer tokuiNo, String hinban) {
        repository.deleteById(new ShohinMasterId(tokuiNo, hinban));
    }
}
