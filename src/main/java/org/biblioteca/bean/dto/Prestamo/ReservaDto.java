package org.biblioteca.bean.dto.Prestamo;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "El cliente es obligatorio")
    @Positive(message = "El ID del cliente debe ser positivo")
    private Integer idCliente;

    @NotNull(message = "El libro es obligatorio")
    @Positive(message = "El ID del libro debe ser positivo")
    private Integer idLibro;

    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha de reserva debe ser hoy o en el futuro")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;
}
