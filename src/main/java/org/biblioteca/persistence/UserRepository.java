package org.biblioteca.persistence;

import org.biblioteca.bean.entity.Login.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByNombreUsuario(String nombreUsuario);
}
