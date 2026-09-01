package org.biblioteca.bean.dto.Inventario;

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
public class CompraDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "El proveedor es obligatorio")
    @Positive(message = "El ID del proveedor debe ser positivo")
    private Integer idProveedor;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @NotNull(message = "El total es obligatorio")
    @PositiveOrZero(message = "El total debe ser mayor o igual a 0")
    private Double total;
}
