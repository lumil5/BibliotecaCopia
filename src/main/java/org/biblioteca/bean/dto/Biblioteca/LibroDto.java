package org.biblioteca.bean.dto.Biblioteca;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idLibro;

    @NotBlank(message = "El ISBN es obligatorio")
    @Size(max = 20, message = "El ISBN no puede exceder los 20 caracteres")
    private String isbn;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede exceder los 100 caracteres")
    private String titulo;

    @Size(max = 100, message = "El subtítulo no puede exceder los 100 caracteres")
    private String subtitulo;

    @NotNull(message = "El año es obligatorio")
    @Positive(message = "El año debe ser un número positivo")
    private Integer anio;

    @NotBlank(message = "La edición es obligatoria")
    @Size(max = 50, message = "La edición no puede exceder los 50 caracteres")
    private String edicion;

    @NotNull(message = "El número de páginas es obligatorio")
    @Positive(message = "El libro debe tener páginas positivas")
    private Integer paginas;

    @NotBlank(message = "El idioma es obligatorio")
    @Size(max = 50, message = "El idioma no puede exceder los 50 caracteres")
    private String idioma;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String descripcion;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    @NotNull(message = "La editorial es obligatoria")
    @Positive(message = "El ID de la editorial debe ser positivo")
    private Integer idEditorial;

    @NotNull(message = "La categoría es obligatoria")
    @Positive(message = "El ID de la categoría debe ser positivo")
    private Integer idCategoria;
}
