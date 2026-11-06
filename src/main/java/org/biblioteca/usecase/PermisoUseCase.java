package org.biblioteca.usecase;

import org.biblioteca.bean.entity.Login.Permiso;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface PermisoUseCase {

  public List<Permiso> listarPorUsuario(int idUsuario);    

}
