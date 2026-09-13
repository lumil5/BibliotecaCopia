package org.biblioteca.persistence;

import java.util.Optional;
import java.util.List;

import org.biblioteca.bean.Enum.TipoEstado;
import org.biblioteca.bean.entity.Administracion.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    boolean existsByDni(String dni);
    Optional<Persona> findByDni(String dni);
    List<Persona> findByEstado(TipoEstado estado);

}


