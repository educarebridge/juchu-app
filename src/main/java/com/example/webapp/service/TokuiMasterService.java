package com.example.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.TokuiMaster;
import com.example.webapp.repository.TokuiMasterRepository;

@Service
public class TokuiMasterService {

    @Autowired
    private TokuiMasterRepository tokuiMasterRepository;

    // 全件取得
    public List<TokuiMaster> findAll() {
        return tokuiMasterRepository.findAll();
    }

    // 主キー（得意先CD）による1件取得
    public TokuiMaster findById(Integer tokuiNo) {
        return tokuiMasterRepository.findById(tokuiNo).orElse(null);
    }

    // 保存（新規登録・更新兼用）
    @Transactional
    public void save(TokuiMaster tokuiMaster) {
        tokuiMasterRepository.save(tokuiMaster);
    }

    // 削除処理
    @Transactional
    public void deleteById(Integer tokuiNo) {
        tokuiMasterRepository.deleteById(tokuiNo);
    }
}
