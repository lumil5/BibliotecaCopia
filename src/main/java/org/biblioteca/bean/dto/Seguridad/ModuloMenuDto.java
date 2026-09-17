package org.biblioteca.bean.dto.Seguridad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuloMenuDto {
    private String nombre;
    private String codigo;
    private String icono;
    private String ruta;
    private boolean puedeLeer;
    private boolean puedeCrear;
    private boolean puedeEditar;
    private boolean puedeEliminar;
    private boolean puedeImprimir;
}
