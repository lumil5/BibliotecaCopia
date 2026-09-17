package org.biblioteca.persistence.seguridad;

import java.util.List;
import java.util.Optional;
import org.biblioteca.bean.entity.Seguridad.UsuarioPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPermisoRepository extends JpaRepository<UsuarioPermiso, Integer> {

    @Query("SELECT up FROM UsuarioPermiso up " +
           "JOIN FETCH up.modulo m " +
           "JOIN up.usuario u " +
           "WHERE u.username = :username " +
           "AND m.estado = true " +
           "ORDER BY m.orden ASC")
    List<UsuarioPermiso> findPermisosActivosByUsername(@Param("username") String username);

    @Query("SELECT up FROM UsuarioPermiso up " +
           "JOIN up.modulo m " +
           "JOIN up.usuario u " +
           "WHERE u.username = :username AND m.codigo = :codigoModulo")
    Optional<UsuarioPermiso> findByUsernameAndCodigoModulo(@Param("username") String username,
                                                           @Param("codigoModulo") String codigoModulo);
}
