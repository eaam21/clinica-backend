package com.clinica.model.dto;

public record PacienteInputDTO(
		Long idPaciente,
		String apellidoPaterno,
		String apellidoMaterno,
		String nombres,
		String dni,
		Float peso,
		Float talla,
		Float imc,
		Integer estado,
		Long idEspecialidad
		) {}
