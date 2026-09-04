package org.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/Libros")
@Controller
public class LibroController {
    @GetMapping("/registrar")
    public String index() {
        return "view/libro/registrarLibro";
    }
}
