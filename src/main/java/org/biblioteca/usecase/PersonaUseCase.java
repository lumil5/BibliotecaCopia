package org.biblioteca.usecase;

import java.util.List;

import org.biblioteca.bean.dto.Administracion.PersonaDto;
import org.springframework.stereotype.Component;

@Component
public interface PersonaUseCase {
  // apunta al dto persona, el controlador enviara el dto para su registro 
  public PersonaDto Registrar(PersonaDto personaDto);
  public PersonaDto Buscar(PersonaDto personaDto);
  public PersonaDto Actualizar(PersonaDto personaDto);
  public PersonaDto Eliminar(PersonaDto personaDto);
  public List<PersonaDto> ListarPorEstado(PersonaDto personaDto);
  public List<PersonaDto> Listar();

} 
