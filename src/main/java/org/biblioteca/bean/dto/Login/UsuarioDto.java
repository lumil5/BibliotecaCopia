// package org.biblioteca.bean.dto.Login;

// import java.io.Serializable;

// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import org.biblioteca.bean.Enum.TipoUsuario;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class UsuarioDto implements Serializable {
//     private static final long serialVersionUID = 1L;

//     private Integer id;

//     @NotBlank(message = "El nombre de usuario es obligatorio")
//     @Size(max = 20, message = "El nombre de usuario no puede exceder los 20 caracteres")
//     private String nombreUsuario;

//     @NotBlank(message = "La contraseña es obligatoria")
//     @Size(max = 15, message = "La contraseña no puede exceder los 15 caracteres")
//     private String clave;
    
//     @NotNull(message = "El nivel de acceso es obligatorio")
//     private TipoUsuario tipoUsuario; 
// }
