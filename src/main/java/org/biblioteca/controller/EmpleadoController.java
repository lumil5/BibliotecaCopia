package org.biblioteca.controller;

import java.util.Arrays;
import java.util.List;

import org.biblioteca.bean.Enum.TipoEstado;
import org.biblioteca.bean.Enum.TipoUsuario;
import org.biblioteca.bean.dto.Administracion.EmpleadoDto;
import org.biblioteca.bean.entity.Login.Permiso;
import org.biblioteca.bean.entity.Login.Usuario;
import org.biblioteca.model.EmpleadoModel;
import org.biblioteca.persistence.PermisoRepository;
import org.biblioteca.usecase.UsuarioUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/empleados")
@AllArgsConstructor
public class EmpleadoController {

    private final EmpleadoModel empleadoModel;
    private final UsuarioUseCase usuarioUseCase;
    private final PermisoRepository permisoRepository;

    @GetMapping("")
    public String Empleados(Model model, Authentication auth) {
        // Buscamos el usuario por el nombre de la sesion actual
        Usuario usuario = usuarioUseCase.FindByUserName(auth.getName());
        // Buscamos los permisos del usuario
        List<Permiso> permisos = permisoRepository.findByUsuario(usuario);
        model.addAttribute("permisos", permisos);

        // Listamos los empleados
        List<EmpleadoDto> empleados = empleadoModel.ListarGeneric();
        model.addAttribute("empleados", empleados);
        model.addAttribute("empleado", new EmpleadoDto());
        model.addAttribute("modoEdicion", false);

        return "view/empleados";
    }

    @GetMapping("/editar")
    public String EditarEmpleado(@RequestParam Integer IdEmpleado, Model model,
            Authentication auth) {
        // Buscamos el usuario por el nombre de la sesion actual
        Usuario usuario = usuarioUseCase.FindByUserName(auth.getName());
        // Buscamos los permisos del usuario
        List<Permiso> permisos = permisoRepository.findByUsuario(usuario);
        model.addAttribute("permisos", permisos);

        // Obtenemos el empleado a editar
        EmpleadoDto empleado = empleadoModel.BuscarIdGeneric(IdEmpleado);
        model.addAttribute("empleado", empleado);

        // Listamos los empleados para mantener la tabla poblada en la vista
        List<EmpleadoDto> empleados = empleadoModel.ListarGeneric();
        model.addAttribute("empleados", empleados);
        model.addAttribute("modoEdicion", false);

        return "view/empleados";
    }

    @PostMapping(params = "registrar")
    public String RegistrarEmpleado(@Valid @ModelAttribute EmpleadoDto empleado, BindingResult result, Model model,
            Authentication auth) {
        // Buscamos el usuario por el nombre de la sesion actual
        Usuario usuario = usuarioUseCase.FindByUserName(auth.getName());
        // Buscamos los permisos del usuario
        List<Permiso> permisos = permisoRepository.findByUsuario(usuario);
        // Verificamos si hay errores
        if (result.hasErrors()) {
            model.addAttribute("permisos", permisos);
            model.addAttribute("listarEmpleados", empleadoModel.ListarGeneric());
            model.addAttribute("modoEdicion", false);
            return "view/empleados";
        }
        // Registramos el empleado
        empleadoModel.RegistrarGeneric(empleado);
        return "redirect:/empleados";
    }

    @GetMapping(params = "eliminar")
    public String EliminarEmpleado(@RequestParam Integer IdEmpleado, Model model,
            Authentication auth) {
        // Buscamos el usuario por el nombre de la sesion actual
        Usuario usuario = usuarioUseCase.FindByUserName(auth.getName());
        // Buscamos los permisos del usuario
        List<Permiso> permisos = permisoRepository.findByUsuario(usuario);
        model.addAttribute("permisos", permisos);

        // Obtenemos el empleado a eliminar
        EmpleadoDto empleado = empleadoModel.BuscarIdGeneric(IdEmpleado);
        // Verificamos si el empleado existe
        if (empleado == null) {
            model.addAttribute("empleado", empleado);
            model.addAttribute("error", "EL REGISTRO NO EXISTE");
        } else {
            empleadoModel.EliminarGeneric(IdEmpleado);
            model.addAttribute("empleado", empleado);
            model.addAttribute("success", "EL REGISTRO SE ELIMINO");
        }

        // Listamos los empleados para mantener la tabla poblada en la vista
        List<EmpleadoDto> empleados = empleadoModel.ListarGeneric();
        model.addAttribute("empleados", empleados);
        model.addAttribute("modoEdicion", false);

        return "view/empleados";
    }

    //flash.addFlashAttribute("success", "REGISTRO ELIMINADO");

    // Enum tipoEstado
    @ModelAttribute("listaEstado")
    public List<TipoEstado> getEstados() {
        return Arrays.asList(TipoEstado.values());
    }

    // Enum tipoUsuario
    @ModelAttribute("listaTipoUsuario")
    public List<TipoUsuario> getTipoUsuarios() {
        return Arrays.asList(TipoUsuario.values());
    }

}
