package org.biblioteca.bean.entity.Biblioteca;

import java.io.Serializable;
import java.util.List;

import org.biblioteca.bean.entity.Prestamo.DetallePrestamo;

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
@Table(name = "ejemplar", schema = "biblioteca")
@Builder
public class Ejemplar implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEjemplar")
    private Integer idEjemplar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idLibro")
    private Libro libro;

    @Column(name = "codigoBarra")
    private String codigoBarra;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "estado")
    private Boolean estado;

    @OneToMany(mappedBy = "ejemplar", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePrestamo> listaDetallesPrestamo;
}
