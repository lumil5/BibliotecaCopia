package org.biblioteca.bean.entity.Prestamo;

import java.io.Serializable;
import java.time.LocalDate;

import org.biblioteca.bean.entity.Administracion.Cliente;
import org.biblioteca.bean.entity.Biblioteca.Libro;

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
@Table(name = "reserva", schema = "prestamo")
@Builder
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReserva")
    private Integer idReserva;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idLibro")
    private Libro libro;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "estado")
    private Boolean estado;
}
