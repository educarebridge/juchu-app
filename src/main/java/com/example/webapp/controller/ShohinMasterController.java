package com.example.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; // ★追加（ビルドエラー防止）
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.webapp.entity.ShohinMaster;
import com.example.webapp.form.ShohinMasterForm;
import com.example.webapp.service.ShohinMasterService;

@Controller
@RequestMapping("/shohin")
public class ShohinMasterController {

    @Autowired
    private ShohinMasterService service;

    // 一覧表示・検索機能・編集データ取得を行う統合メソッド
    @GetMapping("/list")
    public String list(
            @RequestParam(name = "searchTokuiName", required = false) String searchTokuiName,
            @RequestParam(name = "searchHinmei", required = false) String searchHinmei,
            @RequestParam(value = "tokuiNo", required = false) Integer tokuiNo,
            @RequestParam(value = "hinban", required = false) String hinban,
            Model model) {

        // 1. 検索処理
        List<ShohinMaster> list = service.searchShohin(searchTokuiName, searchHinmei);
        model.addAttribute("shohinList", list);

        // 2. 編集データの取得（tokuiNo と hinban が指定されている場合）
        ShohinMaster shohin;
        if (tokuiNo != null && hinban != null) {
            shohin = service.findById(tokuiNo, hinban).orElse(new ShohinMaster());
            model.addAttribute("isEdit", true);
        } else {
            shohin = new ShohinMaster();
            model.addAttribute("isEdit", false);
        }

        model.addAttribute("shohinMaster", shohin);
        model.addAttribute("shohinForm", shohin);

        return "shohin_master";
    }

    // ★【修正】登録・更新処理メソッド
    @PostMapping("/save")
    public String save(@ModelAttribute("shohinForm") ShohinMasterForm form) {

        // 1. Form の値を使って Entity (ShohinMaster) オブジェクトを作成・詰め替え
        ShohinMaster entity = new ShohinMaster();
        entity.setTokuiNo(form.getTokuiNo());
        entity.setTokuiName(form.getTokuiName()); // ★この行を追加します
        entity.setHinban(form.getHinban());
        entity.setHinban2(form.getHinban2());
        entity.setHinmei(form.getHinmei());
        entity.setUnitPrice(form.getUnitPrice());
        entity.setUnitName(form.getUnitName());
        entity.setKikaku(form.getKikaku());
        entity.setContainerGroup(form.getContainerGroup());
        entity.setPickingIrisu(form.getPickingIrisu());

        // 2. 変換した Entity を save メソッドに渡す（ここだけを残す）
        service.save(entity);

        // 処理終了後、一覧画面にリダイレクト
        return "redirect:/shohin/list";
    }

    // ★削除処理メソッド
    @PostMapping("/delete")
    public String delete(
            @RequestParam("tokuiNo") Integer tokuiNo,
            @RequestParam("hinban") String hinban) {
        
        service.deleteById(tokuiNo, hinban);
        return "redirect:/shohin/list";
    }
}
