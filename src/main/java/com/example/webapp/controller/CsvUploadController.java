package com.example.webapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webapp.entity.Tanto;
import com.example.webapp.repository.CsvHeaderDetailWorkRepository;
import com.example.webapp.service.CsvImportService;
import com.example.webapp.service.SalesOrderImportService;
import com.example.webapp.service.TantoService;

@Controller
public class CsvUploadController {

    private final CsvImportService csvImportService;
    private final TantoService tantoService;
    private final SalesOrderImportService salesOrderImportService;
    private final CsvHeaderDetailWorkRepository csvHeaderDetailWorkRepository;

    public CsvUploadController(
            CsvImportService csvImportService, 
            TantoService tantoService,
            SalesOrderImportService salesOrderImportService,
            CsvHeaderDetailWorkRepository csvHeaderDetailWorkRepository) {
        this.csvImportService = csvImportService;
        this.tantoService = tantoService;
        this.salesOrderImportService = salesOrderImportService;
        this.csvHeaderDetailWorkRepository = csvHeaderDetailWorkRepository;
    }

    // CSVアップロード画面用
    @GetMapping("/csv/upload")
    public String showUploadForm(Model model) {
        List<Tanto> tantoList = tantoService.findAll(); 
        model.addAttribute("tantoList", tantoList);

        return "csv_upload"; // csv_upload.html を表示
    }

    // CSVメニュー画面用
    @GetMapping("/csv/menu")
    public String showCsvMenu(Model model) {
        List<Tanto> tantoList = tantoService.findAll(); 
        model.addAttribute("tantoList", tantoList);

        return "csv_menu"; // csv_menu.html を表示
    }

    @PostMapping("/csv_upload")
    public String handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "tantoNo", required = false) Integer tantoNo,
            @RequestParam(value = "tantoName", required = false) String tantoName,
            @RequestParam("seizobiSettei") Integer seizobiSettei,
            @RequestParam(value = "juchuBi", required = false) String juchuBi,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "ファイルを選択してください。");
            return "redirect:/csv_upload";
        }

        try {
            // 1. ワークテーブルの初期化（全件削除）
            csvHeaderDetailWorkRepository.deleteAllInBatch();

            // 2. CSVファイルを一時テーブルに取り込み
            csvImportService.importCsv(file, tantoNo, tantoName, seizobiSettei);

            // 3. ワークテーブルからヘッダー・明細テーブルへ振り分け処理を実行
            salesOrderImportService.processCsvToWorkTables();

            // 4. メッセージをセットして CSVメニュー画面へリダイレクト
            redirectAttributes.addFlashAttribute("message", "CSVファイルの読み込みが完了しました。");
            return "redirect:/csv/menu";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "エラーが発生しました: " + e.getMessage());
            return "redirect:/csv_upload";
        }
    }
}
