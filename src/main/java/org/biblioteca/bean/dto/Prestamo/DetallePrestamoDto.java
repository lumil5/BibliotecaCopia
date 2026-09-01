package org.biblioteca.bean.dto.Prestamo;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetallePrestamoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "El préstamo es obligatorio")
    @Positive(message = "El ID de préstamo debe ser positivo")
    private Integer idPrestamo;

    @NotNull(message = "El ejemplar es obligatorio")
    @Positive(message = "El ID de ejemplar debe ser positivo")
    private Integer idEjemplar;

    @PastOrPresent(message = "La fecha de devolución real no puede ser futura")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaDevolucionReal;

    @PositiveOrZero(message = "La multa debe ser mayor o igual a 0")
    private Double multa;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;
}
