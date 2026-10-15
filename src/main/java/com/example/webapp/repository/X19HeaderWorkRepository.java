package com.example.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webapp.entity.CsvHeaderWork;

@Repository
public interface X19HeaderWorkRepository extends JpaRepository<CsvHeaderWork, Long> {

    List<CsvHeaderWork> findAllByOrderByDenpyoIdAsc();

}
