package org.biblioteca.bean.entity.Login;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu", schema = "login")
@Builder
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private Integer idMenu;

    @Column(name = "nombre_menu")
    private String nombreMenu;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "url_menu")
    private String urlMenu;

    @OneToMany(mappedBy = "idMenu")
    private List<Permiso> permisos;
}
