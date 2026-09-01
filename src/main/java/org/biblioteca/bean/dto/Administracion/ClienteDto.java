package org.biblioteca.bean.dto.Administracion;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "El código es obligatorio")
    @Pattern(regexp = "^[NEP]\\d{4}$", message = "El código debe tener formato N0001, E0001 o P0001")
    private String codigo;

    @NotNull(message = "El tipo de cliente es obligatorio")
    private String tipoCliente;

    @NotNull(message = "La fecha de registro es obligatoria")
    @PastOrPresent(message = "La fecha de registro no puede ser futura")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    // Solo necesita trabajar con integer idpersona para mostrar el valor, no con
    // objetos (sirven para mapear la entidad)
    @NotNull(message = "Debe seleccionar una persona")
    @Positive(message = "El ID de persona debe ser positivo")
    private Integer idPersona;
}
