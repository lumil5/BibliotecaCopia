package org.biblioteca.usecase;

import org.biblioteca.bean.dto.Administracion.PersonaDto;
import org.springframework.stereotype.Component;

@Component
public interface PersonaUseCase {
  public PersonaDto Registrar (PersonaDto personaDto);
  
} 
