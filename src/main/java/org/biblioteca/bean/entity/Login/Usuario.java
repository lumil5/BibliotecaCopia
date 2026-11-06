package org.biblioteca.bean.entity.Login;

import java.io.Serializable;
import java.util.List;

import org.biblioteca.bean.Enum.TipoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario", schema = "login")
@Builder
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "clave")
    private String clave;

    // Relacionado con el enum TipoUsuario
    @Column(name = "nivel_acceso")
    @Enumerated(EnumType.STRING)
    private TipoUsuario nivelAcceso;

    // Mapea todos los campos de permiso
    @OneToMany(mappedBy = "idUsuario")
    private List<Permiso> permisos;
}
