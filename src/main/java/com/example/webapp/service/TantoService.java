package com.example.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.Tanto;
import com.example.webapp.repository.TantoRepository;

@Service
@Transactional
public class TantoService {

    @Autowired
    private TantoRepository tantoRepository;

    /**
     * 担当者一覧を取得する（主キー順）
     */
    public List<Tanto> findAll() {
        return tantoRepository.findAll();
    }

    /**
     * 担当者番号で1件取得する
     */
    public Tanto findById(Integer tantoNo) {
        if (tantoNo == null) {
            return null;
        }
        return tantoRepository.findById(tantoNo).orElse(null);
    }

    /**
     * 担当者情報を保存・更新する
     */
    public Tanto save(Tanto tanto) {
        return tantoRepository.save(tanto);
    }

    /**
     * 担当者番号を指定して削除する
     */
    public void deleteById(Integer tantoNo) {
        if (tantoNo != null && tantoRepository.existsById(tantoNo)) {
            tantoRepository.deleteById(tantoNo);
        }
    }
}
