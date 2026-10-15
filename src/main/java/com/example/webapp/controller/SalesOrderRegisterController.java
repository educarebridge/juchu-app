package com.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webapp.service.SalesOrderRegisterService;

@Controller
public class SalesOrderRegisterController {

private final SalesOrderRegisterService salesOrderRegisterService;

public SalesOrderRegisterController(
        SalesOrderRegisterService salesOrderRegisterService) {
    this.salesOrderRegisterService = salesOrderRegisterService;
}



 /**
 * 読込みデータ追加画面を表示
 */
@GetMapping("/csv/add-form")
public String showAddForm(Model model) {

    // Service等からワークテーブルの件数と重複件数を取得する
    int workCount = salesOrderRegisterService.getWorkCount();
    int existCount = salesOrderRegisterService.getExistCount();

    // 画面(Thymeleaf)へ渡す
    model.addAttribute("workCount", workCount);
    model.addAttribute("existCount", existCount);

    return "csv_add_form";
}


/**
 * x19_headerwork → x19_header
 * ヘッダーデータを追加登録
 */
@PostMapping("/csv/add-header")
public String addHeader(
        RedirectAttributes redirectAttributes) {

    try {

        int count = salesOrderRegisterService.registerHeader();

        redirectAttributes.addFlashAttribute(
                "message",
                "受注伝票を " + count + " 件追加登録しました。"
        );

    } catch (Exception e) {

        e.printStackTrace();

        redirectAttributes.addFlashAttribute(
                "errorMessage",
                "受注伝票の追加登録中にエラーが発生しました。"
        );
    }

    return "redirect:/csv/add-form";
}

}
