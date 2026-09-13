package org.biblioteca.bean.dto.Prestamo;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrestamoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idPrestamo;

    @NotNull(message = "El cliente es obligatorio")
    @Positive(message = "El ID del cliente debe ser positivo")
    private Integer idCliente;

    @NotNull(message = "El empleado es obligatorio")
    @Positive(message = "El ID del empleado debe ser positivo")
    private Integer idEmpleado;

    @NotNull(message = "La fecha de préstamo es obligatoria")
    @PastOrPresent(message = "La fecha de préstamo no puede ser futura")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPrestamo;

    @NotNull(message = "La fecha de devolución es obligatoria")
    @FutureOrPresent(message = "La fecha de devolución debe ser hoy o en el futuro")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaDevolucion;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;
}
