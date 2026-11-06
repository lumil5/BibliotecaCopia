package org.biblioteca.model;

import java.util.List;

import org.biblioteca.bean.entity.Administracion.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoModel extends GenericModel<Empleado, Integer> {

    public EmpleadoModel(JpaRepository<Empleado, Integer> persistenceEmpleado) {
        super(persistenceEmpleado);
    }

    public List<Empleado> ListarGeneric() {
        return super.ListarGeneric();
    }

}