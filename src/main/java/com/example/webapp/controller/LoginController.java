package com.example.webapp.controller; // ご自身のパッケージ名に合わせてください

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // セッションを破棄（ログアウト処理）
        session.invalidate();
        
        // ログアウト後にログイン画面（またはトップ画面）へリダイレクト
        return "redirect:/"; // もしくは "redirect:/login.html" など
    }
}
