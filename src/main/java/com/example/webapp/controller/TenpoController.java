package com.example.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.webapp.entity.TenpoMaster;
import com.example.webapp.repository.TenpoMasterRepository;
import com.example.webapp.repository.TokuiMasterRepository;

@Controller
@RequestMapping("/tenpo")
public class TenpoController {

    @Autowired
    private TenpoMasterRepository tenpoMasterRepository;

    @Autowired
    private TokuiMasterRepository tokuiMasterRepository;

    // 一覧表示 & 新規登録フォーム
    @GetMapping("/list")
    public String list(Model model) {
        // 1. ソート付きで店舗一覧を取得
        List<TenpoMaster> tenpoList = tenpoMasterRepository.findAllByOrderByTokuiNoAscTenpoNoAsc();
        model.addAttribute("tenpoList", tenpoList);

        // 2. 得意先プルダウン用リストの格納
        model.addAttribute("tokuiList", tokuiMasterRepository.findAll());

        // 3. 入力フォーム用オブジェクトの格納
        if (!model.containsAttribute("tenpoMaster")) {
            model.addAttribute("tenpoMaster", new TenpoMaster());
        }

        return "tenpo_list"; // ※ templates/tenpo_list.html の場合
    }

    // 新規登録・更新処理
    @PostMapping("/save")
    public String save(@ModelAttribute TenpoMaster tenpoMaster) {
        tenpoMasterRepository.save(tenpoMaster);
        return "redirect:/tenpo/list";
    }

    // 編集画面表示
    @GetMapping("/edit/{tenpoNo}")
    public String edit(@PathVariable("tenpoNo") Integer tenpoNo, Model model) {
        TenpoMaster tenpo = tenpoMasterRepository.findById(tenpoNo).orElse(new TenpoMaster());
        model.addAttribute("tenpoMaster", tenpo);
        model.addAttribute("tokuiList", tokuiMasterRepository.findAll());
        model.addAttribute("tenpoList", tenpoMasterRepository.findAllByOrderByTokuiNoAscTenpoNoAsc());
        return "tenpo_list";
    }

    // 削除処理
    @GetMapping("/delete/{tenpoNo}")
    public String delete(@PathVariable("tenpoNo") Integer tenpoNo) {
        tenpoMasterRepository.deleteById(tenpoNo);
        return "redirect:/tenpo/list";
    }
}
