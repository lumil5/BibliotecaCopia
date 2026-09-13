package org.biblioteca.controller;

import java.util.Arrays;
import java.util.List;

import org.biblioteca.bean.Enum.TipoEstado;
import org.biblioteca.bean.dto.Administracion.PersonaDto;
import org.biblioteca.exception.PersonaAlreadyExistsException;
import org.biblioteca.exception.PersonaInvalidDataException;
import org.biblioteca.exception.PersonaNotFoundException;
import org.biblioteca.usecase.PersonaUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/Personas")
public class PersonaController {

    private final PersonaUseCase personaUseCase;

    // primero en levantar la pantalla
    @GetMapping
    public String persona(Model model) {
        model.addAttribute("persona", new PersonaDto());
        return "view/persona/persona";
    }

    @PostMapping(params = "addPersona")
    public String RegistrarPersona(@Valid @ModelAttribute("persona") PersonaDto personaDto, BindingResult result,
            Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("persona", personaDto);
            return "view/persona/persona";
        }
        try {
            PersonaDto registrado = personaUseCase.Registrar(personaDto);
            redirectAttributes.addFlashAttribute("mensajeExito",
                    String.format("Se registró correctamente a %s %s", registrado.getNombre(),
                            registrado.getApellidoPaterno()));
        } catch (PersonaAlreadyExistsException e) {
            result.rejectValue("dni", "error.persona", e.getMessage());
            model.addAttribute("persona", personaDto);
            return "view/persona/persona";
        } catch (PersonaInvalidDataException e) {
            model.addAttribute("mensajeError", e.getMessage());
            model.addAttribute("persona", personaDto);
            return "view/persona/persona";
        }
        return "redirect:/Personas";
    }

    @GetMapping("/Listar")
    public String ListarPersonas(Model model) {
        // cargar lista
        try {
            List<PersonaDto> ListaPersonas = personaUseCase.Listar();
            model.addAttribute("ListaPersonas", ListaPersonas);
        } catch (PersonaNotFoundException e) {
            model.addAttribute("mensajeError", e.getMessage());
            return "view/persona/listarPersonas";
        }
        return "view/persona/listarPersonas";
    }

    // Metodo que carga los valores del enum
    @ModelAttribute("listaEstado")
    public List<TipoEstado> getEstados() {
        return Arrays.asList(TipoEstado.values());
    }
}