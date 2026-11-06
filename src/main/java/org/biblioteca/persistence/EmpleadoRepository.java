package org.biblioteca.persistence;

import java.util.Optional;

import org.biblioteca.bean.entity.Administracion.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    // Utiliza atributo de entidad asociado: persona (recomendado)
    // Tambien se puede utilizar atributos de la entidad empleado
    // @Query("SELECT e FROM Empleado e WHERE e.persona.dni = :dni")
    Optional<Empleado> findByPersona_Dni(String dni);

}
