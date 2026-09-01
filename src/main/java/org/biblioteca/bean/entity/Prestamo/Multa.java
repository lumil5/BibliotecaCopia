package org.biblioteca.bean.entity.Prestamo;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "multa", schema = "prestamo")
@Builder
public class Multa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMulta")
    private Integer idMulta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDetallePrestamo")
    private DetallePrestamo detallePrestamo;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "fecha")
    private LocalDate fecha;
}
