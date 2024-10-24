package com.clinica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_paciente")
@Data
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paciente")
	private Long id;

	@Column(name = "apellido_paterno")
	private String apellidopaterno;

	@Column(name = "apellido_materno")
	private String apellidomaterno;

	@Column(name = "nombres")
	private String nombres;

	private String dni;
	private int peso;
	private int talla;
	private double imc;

	@ManyToOne
	@JoinColumn(name = "id_especialidad")
	private Especialidad especialidad;
}