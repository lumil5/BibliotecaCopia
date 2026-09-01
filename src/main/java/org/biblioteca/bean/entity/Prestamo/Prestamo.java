package org.biblioteca.bean.entity.Prestamo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.biblioteca.bean.entity.Administracion.Cliente;
import org.biblioteca.bean.entity.Administracion.Empleado;

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
@Table(name = "prestamo", schema = "prestamo")
@Builder
public class Prestamo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrestamo")
    private Integer idPrestamo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEmpleado")
    private Empleado empleado;

    @Column(name = "fechaPrestamo")
    private LocalDate fechaPrestamo;

    @Column(name = "fechaDevolucion")
    private LocalDate fechaDevolucion;

    @Column(name = "estado")
    private Boolean estado;

    @OneToMany(mappedBy = "prestamo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePrestamo> listaDetalles;
}
