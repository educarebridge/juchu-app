// 修正前: package com.example.juchu.controller;
package com.example.webapp.controller; // 修正後

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // http://localhost:8080/ へのアクセスを処理
    @GetMapping("/")
    public String index() {
        // templates/index.html を返却する
        return "index";
    }
}
