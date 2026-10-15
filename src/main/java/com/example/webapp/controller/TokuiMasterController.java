package com.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.webapp.entity.TokuiMaster;
import com.example.webapp.service.TokuiMasterService;

@Controller
@RequestMapping("/tokuisaki")
public class TokuiMasterController {

    @Autowired
    private TokuiMasterService tokuiMasterService;

    // 一覧表示＆初期入力フォーム表示
    @GetMapping
    public String index(Model model) {
        model.addAttribute("tokuiMaster", new TokuiMaster());
        model.addAttribute("tokuiList", tokuiMasterService.findAll());
        model.addAttribute("isEdit", false); // 新規登録モード判定用
        return "tokuisaki";
    }

    // 保存処理（新規・編集共通）
    @PostMapping("/save")
    public String save(@ModelAttribute TokuiMaster tokuiMaster) {
        tokuiMasterService.save(tokuiMaster);
        return "redirect:/tokuisaki";
    }

    // 編集画面（フォームに選択したデータをセット）
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        TokuiMaster tokui = tokuiMasterService.findById(id);
        if (tokui == null) {
            return "redirect:/tokuisaki";
        }
        model.addAttribute("tokuiMaster", tokui);
        model.addAttribute("tokuiList", tokuiMasterService.findAll());
        model.addAttribute("isEdit", true); // 編集モード判定用
        return "tokuisaki";
    }

    // 削除処理
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        tokuiMasterService.deleteById(id);
        return "redirect:/tokuisaki";
    }
}
