package com.clinica.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.model.Especialidad;
import com.clinica.repository.IEspecialidadRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/consulta")
@AllArgsConstructor
public class EspecialidadController {

	private IEspecialidadRepository especialidadRepository;

	@GetMapping("/listar")
	public List<Especialidad> listarEspecialidades() {
		return especialidadRepository.findAll();
	}
}
