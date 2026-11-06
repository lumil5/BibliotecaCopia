package org.biblioteca.persistence;

import java.util.List;

import org.biblioteca.bean.entity.Login.Permiso;
import org.biblioteca.bean.entity.Login.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {

    public List<Permiso> findByUsuario(Usuario usuario);
}
