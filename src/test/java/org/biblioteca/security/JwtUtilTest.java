package org.biblioteca.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil();
        // Clave secreta HMAC-SHA256 válida (mínimo 256 bits)
        ReflectionTestUtils.setField(jwtUtil, "secret", "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970");
        ReflectionTestUtils.setField(jwtUtil, "expirationMs", 3600000L); // 1 hora
    }

    @Test
    public void testGenerarYValidarToken() {
        UserDetails userDetails = User.builder()
                .username("admin")
                .password("password")
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_ADMIN")))
                .build();

        String token = jwtUtil.generateToken(userDetails);

        assertNotNull(token);
        assertTrue(jwtUtil.validateToken(token), "El token recién generado debe ser válido");
        assertEquals("admin", jwtUtil.getUsernameFromToken(token));
        assertEquals("ROLE_ADMIN", jwtUtil.getRoleFromToken(token));
    }

    @Test
    public void testTokenInvalido() {
        String tokenInvalido = "eyJhbGciOiJIUzI1NiJ9.invalidpayload.invalidtoken";
        assertFalse(jwtUtil.validateToken(tokenInvalido), "Un token malformado debe retornar false");
    }
}
