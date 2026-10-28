package com.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UnderConstructionController {

    @GetMapping("/under-construction")
    public String underConstruction() {
        return "under_construction";
    }
}
