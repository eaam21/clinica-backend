package com.examen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_consulta")
@Data
public class Consulta {
	@Id
	private int id_consulta;
	private String area;
}
