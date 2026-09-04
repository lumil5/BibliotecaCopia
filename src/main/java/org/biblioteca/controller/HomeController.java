package org.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "pattern/_layout";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "view/dashboard";
    }
}

