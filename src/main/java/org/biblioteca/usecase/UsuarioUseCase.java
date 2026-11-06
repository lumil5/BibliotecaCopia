package org.biblioteca.usecase;

import org.biblioteca.bean.entity.Login.Usuario;
import org.springframework.stereotype.Component;

@Component
public interface UsuarioUseCase {
    public Usuario FindByUserName(String userName);
}
