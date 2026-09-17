package org.biblioteca.service;

import java.util.List;
import java.util.stream.Collectors;

import org.biblioteca.bean.dto.Seguridad.ModuloMenuDto;
import org.biblioteca.bean.entity.Seguridad.UsuarioPermiso;
import org.biblioteca.persistence.seguridad.UsuarioPermisoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityMenuService {

    private final UsuarioPermisoRepository usuarioPermisoRepository;

    public List<ModuloMenuDto> obtenerMenuUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() 
                || "anonymousUser".equals(authentication.getPrincipal())) {
            return List.of();
        }

        String username = authentication.getName();
        List<UsuarioPermiso> permisos = usuarioPermisoRepository.findPermisosActivosByUsername(username);

        // Se consultan directamente en BD en cada petición (Garantiza Revocación Inmediata)
        return permisos.stream()
                .filter(UsuarioPermiso::isPuedeLeer) // Solo muestra módulos con permiso de lectura
                .map(p -> ModuloMenuDto.builder()
                        .nombre(p.getModulo().getNombre())
                        .codigo(p.getModulo().getCodigo())
                        .icono(p.getModulo().getIcono())
                        .ruta(p.getModulo().getRuta())
                        .puedeLeer(p.isPuedeLeer())
                        .puedeCrear(p.isPuedeCrear())
                        .puedeEditar(p.isPuedeEditar())
                        .puedeEliminar(p.isPuedeEliminar())
                        .puedeImprimir(p.isPuedeImprimir())
                        .build())
                .collect(Collectors.toList());
    }
}
