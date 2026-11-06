package org.biblioteca.bean.entity.Security.ModelSecurity;

import org.biblioteca.bean.Enum.TipoUsuario;
import org.biblioteca.bean.entity.Login.Usuario;
import org.biblioteca.persistence.LoginRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserFilterModel implements UserDetailsService {
    private final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = loginRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        TipoUsuario roles = usuario.getNivelAcceso() != null ? usuario.getNivelAcceso() : TipoUsuario.ADMINISTRATIVO;
        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getClave())
                .roles(roles.name())
                .build();
    }

}
