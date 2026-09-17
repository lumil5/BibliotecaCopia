package org.biblioteca.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.biblioteca.bean.Enum.TipoEstado;
import org.biblioteca.bean.entity.Seguridad.Rol;
import org.biblioteca.bean.entity.Seguridad.Usuario;
import org.biblioteca.persistence.seguridad.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    private Rol adminRol;

    @BeforeEach
    public void setUp() {
        adminRol = Rol.builder()
                .idRol(1)
                .nombre("ROLE_ADMIN")
                .descripcion("Administrador")
                .build();
    }

    @Test
    public void testLoadUserByUsername_UsuarioActivo_RetornaUserDetailsHabilitado() {
        Usuario usuarioActivo = Usuario.builder()
                .idUsuario(1)
                .username("admin")
                .password("password123")
                .email("admin@biblioteca.org")
                .estado(TipoEstado.ACTIVO)
                .rol(adminRol)
                .build();

        when(usuarioRepository.findByUsername("admin")).thenReturn(Optional.of(usuarioActivo));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("admin");

        assertNotNull(userDetails);
        assertEquals("admin", userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertTrue(userDetails.isEnabled(), "Un usuario ACTIVO debe tener isEnabled() = true");
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    public void testLoadUserByUsername_UsuarioInactivo_RetornaUserDetailsDeshabilitado() {
        Usuario usuarioInactivo = Usuario.builder()
                .idUsuario(2)
                .username("inactivo")
                .password("password123")
                .email("inactivo@biblioteca.org")
                .estado(TipoEstado.INACTIVO)
                .rol(adminRol)
                .build();

        when(usuarioRepository.findByUsername("inactivo")).thenReturn(Optional.of(usuarioInactivo));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("inactivo");

        assertNotNull(userDetails);
        assertEquals("inactivo", userDetails.getUsername());
        assertFalse(userDetails.isEnabled(), "Un usuario INACTIVO debe tener isEnabled() = false");
    }

    @Test
    public void testLoadUserByUsername_UsuarioNoEncontrado_LanzaExcepcion() {
        when(usuarioRepository.findByUsername("desconocido")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("desconocido");
        });
    }
}
