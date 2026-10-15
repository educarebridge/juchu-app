package com.example.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.webapp.entity.Tanto; // ★ 追加
import com.example.webapp.entity.TenpoMaster;
import com.example.webapp.form.JuchuForm;
import com.example.webapp.repository.BumonMasterRepository;
import com.example.webapp.repository.ShohinMasterRepository;
import com.example.webapp.repository.TantoRepository; // ★ 追加
import com.example.webapp.repository.TenpoMasterRepository;
import com.example.webapp.repository.TokuiMasterRepository;
import com.example.webapp.service.JuchuService;

@Controller
@RequestMapping("/juchu")
public class JuchuController {

    @Autowired
    private ShohinMasterRepository shohinMasterRepository;
    
    @Autowired
    private TokuiMasterRepository tokuiMasterRepository;
    
    @Autowired
    private TenpoMasterRepository tenpoMasterRepository;

    @Autowired
    private BumonMasterRepository bumonMasterRepository;

    @Autowired
    private TantoRepository tantoRepository; // ★ 追加

    @Autowired
    private JuchuService juchuService;

    @GetMapping("/slip")
    public String showSlipForm(Model model) {
        model.addAttribute("shohinList", shohinMasterRepository.findAll());
        model.addAttribute("tokuiList", tokuiMasterRepository.findAll());
        model.addAttribute("tenpoList", tenpoMasterRepository.findAll());
        model.addAttribute("bumonList", bumonMasterRepository.findAll());

        List<Tanto> tantoList = tantoRepository.findAll();
        model.addAttribute("tantoList", tantoList);

        model.addAttribute("juchuForm", new JuchuForm());

        return "slip";
    }


    @GetMapping("/api/stores")
    @ResponseBody
    public List<TenpoMaster> getStoresByCustomer(@RequestParam("tokuiNo") Integer tokuiNo) {
        return tenpoMasterRepository.findByTokuiNo(tokuiNo);
    }

    @PostMapping("/save")
    public String save(JuchuForm form) {

        juchuService.save(form);

        return "redirect:/juchu/slip";
    }


}
