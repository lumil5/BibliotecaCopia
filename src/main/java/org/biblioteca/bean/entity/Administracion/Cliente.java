package org.biblioteca.bean.entity.Administracion;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.biblioteca.bean.Enum.TipoCliente;
import org.biblioteca.bean.entity.Prestamo.Prestamo;
import org.biblioteca.bean.entity.Prestamo.Reserva;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "cliente", schema = "administracion")
@Builder
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente")
	private Integer idCliente;

	@Column(name = "codigo")
	private String codigo;

	// Irá como enum (capa intermedia) // @Enum es un tipo de datos especial de
	// java, que permite almacenar un conjunto de valores
	@Column(name = "tipoCliente")
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;

	@Column(name = "fechaRegistro")
	private LocalDate fechaRegistro;

	@Column(name = "estado")
	private boolean estado;

	// Establece la relacion de uno a muchos
	@ManyToOne(fetch = FetchType.EAGER) // Transaccionabilidad manytoOne debe ser rapida (eager)
	@JoinColumn(name = "idpersona")
	private Persona persona;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Prestamo> listaPrestamos;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reserva> listaReservas;

}
