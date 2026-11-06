package org.biblioteca.bean.entity.Login;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permiso", schema = "login")
@Builder
public class Permiso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Integer idPermiso;

    @Column(name = "crear")
    private boolean crear;

    @Column(name = "leer")
    private boolean leer;

    @Column(name = "actualizar")
    private boolean actualizar;

    @Column(name = "imprimir")
    private boolean imprimir;

    @Column(name = "exportar")
    private boolean exportar;

    // Establecer las relaciones con las otras tablas
    // Solo se relaciona con el id
    @ManyToOne
    @JoinColumn(name = "id_menu")
    private Menu idMenu;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;
}
