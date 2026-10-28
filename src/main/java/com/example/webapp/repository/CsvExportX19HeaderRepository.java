package com.example.webapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webapp.entity.X19Header;

@Repository
public interface CsvExportX19HeaderRepository extends JpaRepository<X19Header, Integer> {
    
    // 日付範囲で受注伝票を検索するメソッド
    //List<X19Header> findByJuchuDateBetween(LocalDate startDate, LocalDate endDate);

    List<X19Header> findByJuchuDateBetweenAndTokuiNoBetween(
            LocalDate startDate, LocalDate endDate, 
            Integer startTokuiCode, Integer endTokuiCode);

}
