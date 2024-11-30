package com.clinica.model.dto;

public record LoginOutputDTO(
		Boolean success,
		String respuesta,
		String token
		) {}
