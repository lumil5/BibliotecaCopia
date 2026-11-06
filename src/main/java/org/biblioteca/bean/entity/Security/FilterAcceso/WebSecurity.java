package org.biblioteca.bean.entity.Security.FilterAcceso;

import org.biblioteca.bean.Enum.TipoUsuario;
import org.biblioteca.bean.entity.Security.ModelSecurity.UserFilterModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurity {
        private final UserFilterModel userFilterModel;
        // private final Usuario usuario;

        // Configuracion de la cadena de filtros de seguridad, brinda acceso a las rutas
        // publicas y deniega el acceso a las rutas privadas
        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                String[] roles = {
                                TipoUsuario.ADMINISTRATIVO.name(),
                                TipoUsuario.DOCENTE.name(),
                                TipoUsuario.ESTUDIANTE.name(),
                                TipoUsuario.EXTERNO.name()
                                };

                return http
                                .csrf(AbstractHttpConfigurer::disable)
                                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                                .authorizeHttpRequests(req -> req
                                                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/login",
                                                                "/validar", "/denegado", "/error")
                                                .permitAll()
                                                .requestMatchers("/home").hasAnyRole(roles)
                                                .requestMatchers("/Personas/**").hasAnyRole(roles)
                                                .requestMatchers("/Libros/**").hasAnyRole(roles)
                                                .anyRequest().denyAll())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .loginProcessingUrl("/validar")
                                                .defaultSuccessUrl("/", true)
                                                .failureUrl("/login?error=true")
                                                .permitAll())
                                .exceptionHandling(ex -> ex
                                                .accessDeniedPage("/denegado"))
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout=true")
                                                .permitAll())
                                .httpBasic(Customizer.withDefaults())
                                .userDetailsService(userFilterModel)
                                .build();
        }

        // Encriptacion de la contraseña
        @Bean
        PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
