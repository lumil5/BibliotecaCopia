package org.biblioteca.bean.entity.Prestamo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.biblioteca.bean.entity.Biblioteca.Ejemplar;

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
@Table(name = "detalle_prestamo", schema = "prestamo")
@Builder
public class DetallePrestamo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalle")
    private Integer idDetalle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPrestamo")
    private Prestamo prestamo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEjemplar")
    private Ejemplar ejemplar;

    @Column(name = "fechaDevolucionReal")
    private LocalDate fechaDevolucionReal;

    @Column(name = "multa")
    private Double multa;

    @Column(name = "estado")
    private Boolean estado;

    @OneToMany(mappedBy = "detallePrestamo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Multa> listaMultas;
}
