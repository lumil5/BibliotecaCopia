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
public class EjemplarDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idEjemplar;

    @NotNull(message = "El libro es obligatorio")
    @Positive(message = "El ID del libro debe ser positivo")
    private Integer idLibro;

    @NotBlank(message = "El código de barra es obligatorio")
    @Size(max = 20, message = "El código de barra no puede exceder los 20 caracteres")
    private String codigoBarra;

    @NotBlank(message = "La ubicación es obligatoria")
    @Size(max = 50, message = "La ubicación no puede exceder los 50 caracteres")
    private String ubicacion;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;
}
