package org.biblioteca.persistence.seguridad;

import java.util.List;
import java.util.Optional;
import org.biblioteca.bean.entity.Seguridad.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Integer> {
    Optional<Modulo> findByCodigo(String codigo);
    List<Modulo> findByEstadoTrueOrderByOrdenAsc();
}
