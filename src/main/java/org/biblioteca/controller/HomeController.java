package org.biblioteca.controller;

import java.util.List;

import org.biblioteca.bean.dto.Seguridad.ModuloMenuDto;
import org.biblioteca.service.SecurityMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SecurityMenuService securityMenuService;

    @ModelAttribute("menuModulos")
    public List<ModuloMenuDto> cargarMenuUsuario() {
        return securityMenuService.obtenerMenuUsuarioAutenticado();
    }

    @GetMapping("/")
    public String index() {
        // Retorna el cascarón/layout base principal (Navbar + Iframe + Footer)
        return "pattern/_layout";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "view/dashboard";
    }
}
