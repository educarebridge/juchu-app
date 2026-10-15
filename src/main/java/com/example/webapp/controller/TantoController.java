package com.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.webapp.entity.Tanto;
import com.example.webapp.repository.TantoRepository;

@Controller
@RequestMapping("/tanto")
public class TantoController {

    @Autowired
    private TantoRepository tantoRepository;

    // 一覧表示・新規入力画面（初期状態）
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("tantoList", tantoRepository.findAll());
        model.addAttribute("tantoForm", new Tanto());
        model.addAttribute("isEdit", false);
        return "tanto_list";
    }

    // 保存処理（新規作成・更新共通）
    @PostMapping("/save")
    public String save(@ModelAttribute("tantoForm") Tanto tanto) {
        tantoRepository.save(tanto);
        return "redirect:/tanto/list";
    }

    // 編集ボタンを押した時の処理（フォームにデータをセット）
    @GetMapping("/edit/{no}")
    public String edit(@PathVariable("no") Integer no, Model model) {
        Tanto tanto = tantoRepository.findById(no).orElse(new Tanto());
        model.addAttribute("tantoList", tantoRepository.findAll());
        model.addAttribute("tantoForm", tanto);
        model.addAttribute("isEdit", true);
        return "tanto_list";
    }

    // 削除処理
    @GetMapping("/delete/{no}")
    public String delete(@PathVariable("no") Integer no) {
        tantoRepository.deleteById(no);
        return "redirect:/tanto/list";
    }
}
