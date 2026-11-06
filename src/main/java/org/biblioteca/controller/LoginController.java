package org.biblioteca.controller;

import org.biblioteca.bean.entity.Login.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {


    @GetMapping("/login")
    public String login(Usuario usuario, Model model) {
        model.addAttribute("usuario", new Usuario());
        return "view/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout=true";
    }

    @GetMapping("/denegado")
    public String denegado() {
        return "view/denegado";
    }
}