package org.biblioteca.bean.dto.Inventario;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
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
public class DetalleCompraDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idDetalleCompra;

    @NotNull(message = "La compra es obligatoria")
    @Positive(message = "El ID de la compra debe ser positivo")
    private Integer idCompra;

    @NotNull(message = "El libro es obligatorio")
    @Positive(message = "El ID del libro debe ser positivo")
    private Integer idLibro;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio debe ser mayor o igual a 0")
    private Double precio;
}
