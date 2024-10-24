package com.examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.model.Consulta;
import com.examen.repository.IConsultaRepository;

@RestController
@RequestMapping("/api/consulta")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaController {
	@Autowired
	private IConsultaRepository repoconsulta;

	@GetMapping
	public List<Consulta> listarconsulta() {
		return repoconsulta.findAll();
	}
}
