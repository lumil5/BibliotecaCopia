package org.biblioteca.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.biblioteca.usecase.PermisoUseCase;
import org.biblioteca.bean.entity.Login.Menu;
import org.biblioteca.bean.entity.Login.Permiso;
import org.biblioteca.bean.entity.Login.Usuario;
import org.biblioteca.persistence.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Controller
public class HomeController {
    private final UserRepository userRepository;
    private final PermisoUseCase permisoUseCase;

    @GetMapping({ "/home" })
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String rol = auth.getAuthorities()
                            .stream()
                            .findFirst()
                            .map(granted -> granted
                                    .getAuthority()
                                    .replace("ROLE ", ""))
                            .orElse("UNKNOWN");
        model.addAttribute("usuario", username);
        model.addAttribute("rol", rol);

        Usuario usuarioPrivilegio = userRepository.findByNombreUsuario(username);    
        List<Permiso> permisos = permisoUseCase.listarPorUsuario(usuarioPrivilegio.getIdUsuario());
        List<Menu> menus = permisos.stream()
                                .map(Permiso::getIdMenu)
                                .filter(menu -> menu != null)
                                .distinct()
                                .collect(Collectors.toList());        
        model.addAttribute("permisos", permisos);
        model.addAttribute("privilegio", menus);

        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fomatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String fechaActualFormateada = fechaActual.format(formatoFecha);
        String horaActualFormateada = fechaActual.format(fomatoHora);
        model.addAttribute("fechaActual", fechaActualFormateada);
        model.addAttribute("horaActual", horaActualFormateada);
        
        return "pattern/_layout";
    }

    // Mientras tenga datos hardcodeados(planos) es uso simple
    @GetMapping("/dashboard")
    public String dashboard() {
        return "view/dashboard";
    }
}

