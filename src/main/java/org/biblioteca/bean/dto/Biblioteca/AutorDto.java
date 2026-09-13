package org.biblioteca.bean.dto.Biblioteca;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idAutor;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no puede exceder los 50 caracteres")
    private String apellido;

    @NotBlank(message = "El país es obligatorio")
    @Size(max = 50, message = "El país no puede exceder los 50 caracteres")
    private String pais;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;
}
