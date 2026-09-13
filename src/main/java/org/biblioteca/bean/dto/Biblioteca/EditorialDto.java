package org.biblioteca.bean.dto.Biblioteca;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class EditorialDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idEditorial;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombre;

    @NotBlank(message = "El país es obligatorio")
    @Size(max = 50, message = "El país no puede exceder los 50 caracteres")
    private String pais;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\d{9}$", message = "El teléfono debe tener 9 dígitos")
    private String telefono;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    private String correo;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;
}
