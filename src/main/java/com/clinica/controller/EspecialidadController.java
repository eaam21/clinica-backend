package com.clinica.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.model.entity.Especialidad;
import com.clinica.model.repository.IEspecialidadRepository;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/especialidad")
@AllArgsConstructor
public class EspecialidadController {

	private IEspecialidadRepository especialidadRepository;

	@GetMapping("/listar")
	public List<Especialidad> listarEspecialidades() {
		return especialidadRepository.findAll();
	}
}
