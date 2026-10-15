package com.example.webapp.repository; // ご自身のパッケージ名に合わせて変更してください

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webapp.entity.Tanto;

@Repository
public interface TantoRepository extends JpaRepository<Tanto, Integer> {
    // JpaRepositoryを継承することで、findAll() や findById() などの基本操作が自動で使えます
}
