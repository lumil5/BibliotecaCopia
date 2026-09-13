package org.biblioteca.bean.dto.Inventario;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProveedorDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idProveedor;

    @NotBlank(message = "El RUC es obligatorio")
    @Pattern(regexp = "^\\d{11}$", message = "El RUC debe tener 11 dígitos")
    private String ruc;

    @NotBlank(message = "La razón social es obligatoria")
    @Size(max = 100, message = "La razón social no puede exceder los 100 caracteres")
    private String razonSocial;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\d{9}$", message = "El teléfono debe tener 9 dígitos")
    private String telefono;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    private String correo;
}
