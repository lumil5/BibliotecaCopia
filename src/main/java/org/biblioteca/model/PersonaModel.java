package org.biblioteca.model;

import java.util.List;

import org.biblioteca.bean.dto.Administracion.PersonaDto;
import org.biblioteca.bean.entity.Administracion.Persona;
import org.biblioteca.exception.PersonaAlreadyExistsException;
import org.biblioteca.exception.PersonaInvalidDataException;
import org.biblioteca.exception.PersonaNotFoundException;
import org.biblioteca.persistence.PersonaRepository;
import org.biblioteca.usecase.PersonaUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PersonaModel implements PersonaUseCase {

    private final PersonaRepository personaRepository;

    @Override
    @Transactional
    public PersonaDto Registrar(PersonaDto personaDto) {
        if (personaDto == null) {
            throw new PersonaInvalidDataException("Los datos de la persona a registrar no pueden ser nulos.");
        }
        if (personaRepository.existsByDni(personaDto.getDni())) {
            throw new PersonaAlreadyExistsException(
                    "La persona con DNI " + personaDto.getDni() + " ya se encuentra registrada.");
        }

        // Conversion DTO -> Entity
        Persona persona = mapPersonaDtoToPersona(personaDto);

        // Guardado en la base de datos
        Persona personaGuardada = personaRepository.save(persona);

        return mapPersonaToPersonaDto(personaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaDto Buscar(PersonaDto personaDto) {
        if (personaDto == null) {
            throw new PersonaInvalidDataException("El parámetro de búsqueda no puede ser nulo.");
        }

        Persona persona;

        if (personaDto.getIdPersona() != null) {
            persona = personaRepository.findById(personaDto.getIdPersona())
                    .orElseThrow(() -> new PersonaNotFoundException(
                            "No se encontró la persona con ID: " + personaDto.getIdPersona()));
        } else if (personaDto.getDni() != null && !personaDto.getDni().isBlank()) {
            persona = personaRepository.findByDni(personaDto.getDni())
                    .orElseThrow(() -> new PersonaNotFoundException(
                            "No se encontró la persona con DNI: " + personaDto.getDni()));
        } else {
            throw new PersonaInvalidDataException(
                    "Debe proporcionar el ID o DNI de la persona para realizar la búsqueda.");
        }

        return mapPersonaToPersonaDto(persona);
    }

    @Override
    @Transactional
    public PersonaDto Actualizar(PersonaDto personaDto) {
        if (personaDto == null || personaDto.getIdPersona() == null) {
            throw new PersonaInvalidDataException("El ID de la persona es obligatorio para actualizar.");
        }
        if (!personaRepository.existsById(personaDto.getIdPersona())) {
            throw new PersonaNotFoundException("No se encontró la persona con ID: " + personaDto.getIdPersona());
        }

        Persona persona = mapPersonaDtoToPersona(personaDto);
        Persona personaActualizada = personaRepository.save(persona);

        return mapPersonaToPersonaDto(personaActualizada);
    }

    @Override
    @Transactional
    public PersonaDto Eliminar(PersonaDto personaDto) {
        if (personaDto == null || personaDto.getIdPersona() == null) {
            throw new PersonaInvalidDataException("El ID de la persona es obligatorio para eliminar.");
        }
        Persona persona = personaRepository.findById(personaDto.getIdPersona())
                .orElseThrow(() -> new PersonaNotFoundException(
                        "No se encontró la persona con ID: " + personaDto.getIdPersona()));

        personaRepository.delete(persona);

        return mapPersonaToPersonaDto(persona);
    }

    @Override
    public List<PersonaDto> ListarPorEstado(PersonaDto personaDto) {
        if (personaDto.getEstado() == null) {
            throw new PersonaInvalidDataException("El estado no puede ser nulo. Elija un estado para listar.");
        }
        List<Persona> personas = personaRepository.findByEstado(personaDto.getEstado());
        List<PersonaDto> personasDto = personas.stream()
                .map(this::mapPersonaToPersonaDto)
                .toList();
        return personasDto;
    }

    @Override
    public List<PersonaDto> Listar() {
        List<Persona> personas = personaRepository.findAll();

        if (personas == null || personas.isEmpty()) {
            throw new PersonaNotFoundException("No se encontraron personas registradas.");
        }
        List<PersonaDto> personasDto = personas.stream()
                .map(this::mapPersonaToPersonaDto)
                .toList();
        return personasDto;
    }

    // patron wrapper => factory: metodo privado para convertir DTO a Entity
    private Persona mapPersonaDtoToPersona(PersonaDto personaDto) {
        return Persona.builder()
                .idPersona(personaDto.getIdPersona())
                .dni(personaDto.getDni())
                .nombre(personaDto.getNombre())
                .apellidoPaterno(personaDto.getApellidoPaterno())
                .apellidoMaterno(personaDto.getApellidoMaterno())
                .direccion(personaDto.getDireccion())
                .telefono(personaDto.getTelefono())
                .correo(personaDto.getCorreo())
                .fechaNacimiento(personaDto.getFechaNacimiento())
                .sexo(personaDto.getSexo())
                .estado(personaDto.getEstado())
                .build();
    }

    // patron wrapper => factory: metodo privado para convertir Entity a DTO
    private PersonaDto mapPersonaToPersonaDto(Persona persona) {
        return PersonaDto.builder()
                .idPersona(persona.getIdPersona())
                .dni(persona.getDni())
                .nombre(persona.getNombre())
                .apellidoPaterno(persona.getApellidoPaterno())
                .apellidoMaterno(persona.getApellidoMaterno())
                .direccion(persona.getDireccion())
                .telefono(persona.getTelefono())
                .correo(persona.getCorreo())
                .fechaNacimiento(persona.getFechaNacimiento())
                .sexo(persona.getSexo())
                .estado(persona.getEstado())
                .build();
    }

}