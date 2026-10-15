package com.example.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.webapp.entity.CsvHeaderWork;
import com.example.webapp.entity.X19Detail;
import com.example.webapp.entity.X19Header;
import com.example.webapp.repository.X19DetailRepository;
import com.example.webapp.repository.X19DetailWorkRepository;
import com.example.webapp.repository.X19HeaderRepository;
import com.example.webapp.repository.X19HeaderWorkRepository;
import com.example.webapp.service.OrderService;


@Controller
public class OrderController {

    @Autowired
    private X19HeaderRepository x19HeaderRepository;

    @Autowired
    private X19HeaderWorkRepository x19HeaderWorkRepository;

    @Autowired
    private X19DetailWorkRepository x19DetailWorkRepository;

    @Autowired
    private X19DetailRepository x19DetailRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/list")
    public String showOrderList(Model model) {

        List<X19Header> orderList =
                x19HeaderRepository.findAllByOrderByDenpyoIdDesc();

        model.addAttribute("orderList", orderList);

        return "order_list";
    }

    @GetMapping("/order/work-list")
    public String showWorkOrderList(Model model) {

        List<CsvHeaderWork> workOrderList =
                x19HeaderWorkRepository.findAllByOrderByDenpyoIdAsc();

        model.addAttribute("workOrderList", workOrderList);

        return "order_work_list";
    }


    @GetMapping("/order_detail")
    public String showOrderDetail(
            @RequestParam("denpyoId") Long denpyoId,
            Model model) {

        CsvHeaderWork header =
                x19HeaderWorkRepository.findById(denpyoId).orElse(null);

        model.addAttribute("header", header);

        List<X19Detail> detailList =
                x19DetailWorkRepository.findByDenpyoId(denpyoId.intValue());

        model.addAttribute("detailList", detailList);

        return "order_detail";
    }


    @GetMapping("/order/slip")
    public String showOrderSlip(
            @RequestParam("denpyoId") Integer denpyoId,
            Model model) {

        X19Header header =
                x19HeaderRepository.findById(denpyoId).orElse(null);

        model.addAttribute("header", header);

       List<X19Detail> detailList =
                x19DetailRepository.findByHeader_DenpyoIdOrderByMeisaiId(denpyoId);

       model.addAttribute("detailList", detailList);

        // =========================
        // レコード移動用
        // =========================

        List<X19Header> orderList =
                x19HeaderRepository.findAllByOrderByDenpyoIdDesc();

        int currentPosition = 0;

        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getDenpyoId().equals(denpyoId)) {
                currentPosition = i;
                break;
            }
        }

        int totalCount = orderList.size();

        model.addAttribute("currentPosition", currentPosition + 1);
        model.addAttribute("totalCount", totalCount);

        // 最初のレコード
        if (totalCount > 0) {
            model.addAttribute(
                    "firstDenpyoId",
                    orderList.get(0).getDenpyoId());
        }

        // 前のレコード
        if (currentPosition > 0) {
            model.addAttribute(
                    "previousDenpyoId",
                    orderList.get(currentPosition - 1).getDenpyoId());
        }

        // 次のレコード
        if (currentPosition < totalCount - 1) {
            model.addAttribute(
                    "nextDenpyoId",
                    orderList.get(currentPosition + 1).getDenpyoId());
        }

        // 最後のレコード
        if (totalCount > 0) {
            model.addAttribute(
                    "lastDenpyoId",
                    orderList.get(totalCount - 1).getDenpyoId());
        }

        return "slip_view";
    }

    // =========================
    // 正式な受注伝票を1件削除
    // =========================
    @PostMapping("/order/delete")
    public String deleteOrder(
            @RequestParam("denpyoId") Integer denpyoId) {

        orderService.deleteOrder(denpyoId);

        return "redirect:/order/list";
    }



}
