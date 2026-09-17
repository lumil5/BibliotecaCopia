package org.biblioteca.bean.entity.Seguridad;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario_permiso", schema = "seguridad",
       uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_modulo"}))
@Builder
public class UsuarioPermiso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_permiso")
    private Integer idUsuarioPermiso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_modulo", nullable = false)
    private Modulo modulo;

    @Column(name = "puede_leer")
    private boolean puedeLeer;

    @Column(name = "puede_crear")
    private boolean puedeCrear;

    @Column(name = "puede_editar")
    private boolean puedeEditar;

    @Column(name = "puede_eliminar")
    private boolean puedeEliminar;

    @Column(name = "puede_imprimir")
    private boolean puedeImprimir;
}
