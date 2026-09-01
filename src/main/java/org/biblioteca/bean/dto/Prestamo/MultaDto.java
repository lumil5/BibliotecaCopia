package org.biblioteca.bean.dto.Prestamo;

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
public class MultaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "El detalle de préstamo es obligatorio")
    @Positive(message = "El ID del detalle de préstamo debe ser positivo")
    private Integer idDetallePrestamo;

    @NotNull(message = "El monto es obligatorio")
    @PositiveOrZero(message = "El monto debe ser mayor o igual a 0")
    private Double monto;

    @NotBlank(message = "El motivo es obligatorio")
    @Size(max = 200, message = "El motivo no puede exceder los 200 caracteres")
    private String motivo;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
}
