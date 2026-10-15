package com.example.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.CsvHeaderDetailWork;
import com.example.webapp.repository.CsvHeaderDetailWorkRepository;
import com.example.webapp.repository.X19DetailRepository;
import com.example.webapp.repository.X19HeaderRepository;

@Service
public class OrderService {

    @Autowired
    private CsvHeaderDetailWorkRepository csvHeaderDetailWorkRepository;

    @Autowired
    private X19HeaderRepository x19HeaderRepository;

    @Autowired
    private X19DetailRepository x19DetailRepository;

    /**
     * ヘッダー一覧（ワークデータ一覧）を取得
     */
    public List<CsvHeaderDetailWork> findAllHeaders() {
        return csvHeaderDetailWorkRepository.findAll();
    }

    /**
     * ID指定でヘッダー（単一ワークデータ）を取得
     */
    public CsvHeaderDetailWork findHeaderByWorkId(Integer workId) {
        if (workId == null) return null;
        return csvHeaderDetailWorkRepository.findById(workId.longValue()).orElse(null);
    }

    /**
     * IDまたは伝票番号指定で明細リストを取得
     */
    public List<CsvHeaderDetailWork> findDetailsByWorkId(Integer workId) {
        // 全件の中から該当のworkId（またはdenpyoId）に一致するものを検索
        // 実際の業務ロジックに合わせてリポジトリの検索条件を適宜変更してください
        return csvHeaderDetailWorkRepository.findAll();
    }

    /**
     * 正式な受注伝票を1件削除
     */
    @Transactional
    public void deleteOrder(Integer denpyoId) {
        if (denpyoId == null) {
            return;
        }

        // 明細を先に削除
        x19DetailRepository.deleteByDenpyoId(denpyoId);

        // ヘッダーを削除
        x19HeaderRepository.deleteByDenpyoId(denpyoId);
    }
}
