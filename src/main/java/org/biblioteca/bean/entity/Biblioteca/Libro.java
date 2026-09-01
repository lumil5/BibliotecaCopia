package org.biblioteca.bean.entity.Biblioteca;

import java.io.Serializable;
import java.util.List;

import org.biblioteca.bean.entity.Prestamo.Reserva;
import org.biblioteca.bean.entity.Inventario.DetalleCompra;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "libro", schema = "biblioteca")
@Builder
public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibro")
    private Integer idLibro;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "subtitulo")
    private String subtitulo;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "edicion")
    private String edicion;

    @Column(name = "paginas")
    private Integer paginas;

    @Column(name = "idioma")
    private String idioma;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private Boolean estado;

    //Relacion de muchos a uno con editorial
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEditorial")
    private Editorial editorial;

    //Relacion de muchos a uno con categoria
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    //La clase libro mapea las siguientes clases
    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LibroAutor> listaAutores;

    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ejemplar> listaEjemplares;

    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> listaReservas;

    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCompra> listaDetallesCompra;
}
