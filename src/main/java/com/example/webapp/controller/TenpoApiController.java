package com.example.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp.entity.TenpoMaster;
import com.example.webapp.repository.TenpoMasterRepository;

@RestController
@RequestMapping("/api/tenpo")
public class TenpoApiController {

    @Autowired
    private TenpoMasterRepository tenpoMasterRepository;

    @GetMapping("/list")
    public List<TenpoMaster> getTenpoList(@RequestParam("tokuisakiNo") String tokuisakiNo) {
        // Repositoryから取得してJSONとして返却
        return tenpoMasterRepository.findByTokuiNoOrderByTenpoNoAsc(tokuisakiNo);
    }
}
