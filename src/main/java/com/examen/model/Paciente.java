package com.examen.model;

import jakarta.persistence.Entity;
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
	private int id;
	private String apellidopaterno;
	private String apellidomaterno;
	private String nombres;
	private String dni;
	private int peso;
	private int talla;
	private double imc;
	private int id_consulta;
	
	@ManyToOne
	@JoinColumn(name = "id_consulta", insertable = false, updatable = false)
	private Consulta objconsulta;
}
