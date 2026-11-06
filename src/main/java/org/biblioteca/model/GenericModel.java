package org.biblioteca.model;

import java.io.Serializable;
import java.util.List;
import org.biblioteca.usecase.GenericUseCase;
import org.springframework.data.jpa.repository.JpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenericModel<T, ID extends Serializable> implements GenericUseCase<T, ID> {
    private final JpaRepository<T, ID> jpaRepository;

    @Override
    public List<T> ListarGeneric() {
        return jpaRepository.findAll();
    }

    @Override
    public T BuscarIdGeneric(ID id) {
        return jpaRepository.findById(id).get();
    }

    @Override
    public void RegistrarGeneric(T t) {
        jpaRepository.save(t);
    }

    @Override
    public void ActualizarGeneric(T t) {
        jpaRepository.save(t);
    }

    @Override
    public boolean EliminarGeneric(ID id) {
        try {
            jpaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
