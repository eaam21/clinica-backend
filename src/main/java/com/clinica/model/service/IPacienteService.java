package com.clinica.model.service;

import java.util.List;

import com.clinica.model.dto.PacienteInputDTO;
import com.clinica.model.entity.Paciente;

public interface IPacienteService {
	List<Paciente> listar();
	Paciente registrar(PacienteInputDTO pacienteDTO);
	Paciente buscarPorId(Long id);
	Paciente actualizar(Long idPaciente, PacienteInputDTO pacienteDTO);
	Paciente eliminar(Long idPaciente);
}
