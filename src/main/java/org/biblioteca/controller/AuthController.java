package org.biblioteca.controller;

import org.biblioteca.bean.dto.Seguridad.LoginRequestDto;
import org.biblioteca.security.JwtAuthenticationFilter;
import org.biblioteca.security.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @GetMapping("/auth/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("mensajeError", "Credenciales inválidas o cuenta desactivada.");
        }
        if (logout != null) {
            model.addAttribute("mensajeExito", "Has cerrado sesión correctamente.");
        }
        model.addAttribute("loginDto", new LoginRequestDto());
        return "view/auth/login";
    }

    @PostMapping("/auth/login")
    public String processLogin(@ModelAttribute("loginDto") LoginRequestDto loginDto,
                               HttpServletResponse response,
                               Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            // Construcción explícita de Cookie HTTP-Only Segura con SameSite=Strict
            ResponseCookie cookie = ResponseCookie.from(JwtAuthenticationFilter.COOKIE_NAME, token)
                    .httpOnly(true)
                    .secure(false) // Establecer a true en producción bajo HTTPS
                    .sameSite("Strict")
                    .path("/")
                    .maxAge(jwtUtil.getExpirationMs() / 1000)
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            // Redirección HTTP 302 a la raíz / (que carga el layout base pattern/_layout)
            return "redirect:/";

        } catch (DisabledException e) {
            return "redirect:/auth/login?error=disabled";
        } catch (AuthenticationException e) {
            return "redirect:/auth/login?error=true";
        }
    }

    @PostMapping("/auth/logout")
    public String processLogout(HttpServletResponse response) {
        // Expira la cookie HTTP-Only en el navegador cliente (Max-Age=0)
        ResponseCookie cookie = ResponseCookie.from(JwtAuthenticationFilter.COOKIE_NAME, "")
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        SecurityContextHolder.clearContext();

        return "redirect:/auth/login?logout=true";
    }

    @GetMapping("/denegado")
    public String accessDeniedPage() {
        return "view/auth/denegado";
    }
}
