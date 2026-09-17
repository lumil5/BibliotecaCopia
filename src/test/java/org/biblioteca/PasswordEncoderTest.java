package org.biblioteca;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void testBCrypt() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashInSchema = "$2a$10$iVK9kD0Q8pP0nJn.y70Jv.3KKHkKSPn9c8j7Bh0erQrmme8R1MLw2";
        boolean matches = encoder.matches("admin123", hashInSchema);
        org.junit.jupiter.api.Assertions.assertTrue(matches, "El hash BCrypt para admin123 debe coincidir");
    }
}
