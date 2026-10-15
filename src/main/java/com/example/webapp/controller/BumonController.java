package com.example.webapp.controller; // パッケージ名は既存に合わせてください

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.webapp.entity.BumonMaster;
import com.example.webapp.repository.BumonMasterRepository;

@Controller
@RequestMapping("/bumon")
public class BumonController {

    @Autowired
    private BumonMasterRepository bumonRepository;

    // 一覧・入力画面の表示
    @GetMapping("/list")
    public String list(Model model) {
        List<BumonMaster> bumonList = bumonRepository.findAllByOrderByBumonNoAsc();
        model.addAttribute("bumonList", bumonList);
        model.addAttribute("bumonForm", new BumonMaster());
        return "bumon_list";
    }

    // 編集時のデータ読み込み
    @GetMapping("/edit/{bumonNo}")
    public String edit(@PathVariable("bumonNo") Integer bumonNo, Model model) {
        List<BumonMaster> bumonList = bumonRepository.findAllByOrderByBumonNoAsc();
        model.addAttribute("bumonList", bumonList);

        BumonMaster target = bumonRepository.findById(bumonNo).orElse(new BumonMaster());
        model.addAttribute("bumonForm", target);
        model.addAttribute("isEdit", true);
        return "bumon_list";
    }

    // 保存処理（新規登録・更新）
    @PostMapping("/save")
    public String save(@ModelAttribute BumonMaster bumonForm) {
        if (bumonForm.getBumonNo() != null) {
            bumonRepository.save(bumonForm);
        }
        return "redirect:/bumon/list";
    }

    // 削除処理
    @GetMapping("/delete/{bumonNo}")
    public String delete(@PathVariable("bumonNo") Integer bumonNo) {
        bumonRepository.deleteById(bumonNo);
        return "redirect:/bumon/list";
    }
}
