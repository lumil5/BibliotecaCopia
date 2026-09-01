package org.biblioteca.bean.entity.Administracion;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.biblioteca.bean.entity.Prestamo.Prestamo;

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
@Table (name = "empleado", schema = "administracion")
@Builder
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEmpleado")
	private Integer idEmpleado;
	
	@Column(name = "cargo")
	private String cargo;
	
	@Column(name = "fechaIngreso")
	private LocalDate fechaIngreso;
	
	@Column(name = "sueldo")
	private Double sueldo;
	
	//Establece la relacion de muchos a uno
	@ManyToOne(fetch = FetchType.EAGER)//Transaccionabilidad manytoOne debe ser rapida (eager)
	@JoinColumn(name = "idpersona")
	private Persona persona;

	@OneToMany(mappedBy = "empleado", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Prestamo> listaPrestamos;
}
