package org.biblioteca.bean.dto.Administracion;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "El cargo es obligatorio")
    @Size(max = 50, message = "El cargo no puede exceder los 50 caracteres")
    private String cargo;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;

    @NotNull(message = "El sueldo es obligatorio")
    @PositiveOrZero(message = "El sueldo debe ser mayor o igual a 0")
    private Double sueldo;

    @NotNull(message = "Debe seleccionar una persona")
    @Positive(message = "El ID de persona debe ser positivo")
    private Integer idPersona;
}
