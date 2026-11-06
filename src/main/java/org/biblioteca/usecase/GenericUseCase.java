package org.biblioteca.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

@Component  
public interface GenericUseCase<T, ID> {
    public List<T> ListarGeneric();
    public T BuscarIdGeneric(ID id);
    public void RegistrarGeneric(T t);
    public void ActualizarGeneric(T t);
    public boolean EliminarGeneric(ID id);
}