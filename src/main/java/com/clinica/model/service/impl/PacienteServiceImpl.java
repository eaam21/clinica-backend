package com.clinica.model.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinica.model.Especialidad;
import com.clinica.model.Paciente;
import com.clinica.model.dto.PacienteInputDTO;
import com.clinica.model.service.IPacienteService;
import com.clinica.repository.IPacienteRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PacienteServiceImpl implements IPacienteService {

	private IPacienteRepository pacienteRepository;
	
	@Override
	public List<Paciente> listar() {
		return pacienteRepository.findAll();
	}
	
	@Override
	@Transactional
	public Paciente registrar(PacienteInputDTO pacienteDTO) {
		Paciente paciente = new Paciente();
		paciente.setApellidoPaterno(pacienteDTO.apellidoPaterno());
		paciente.setApellidoMaterno(pacienteDTO.apellidoMaterno());
		paciente.setNombres(pacienteDTO.nombres());
		paciente.setDni(pacienteDTO.nombres());
		paciente.setPeso(pacienteDTO.peso());
		paciente.setTalla(pacienteDTO.talla());
		paciente.setImc(pacienteDTO.imc());
		Especialidad especialidad = new Especialidad();
		especialidad.setId(pacienteDTO.idEspecialidad());
		paciente.setEspecialidad(especialidad);
		return pacienteRepository.save(paciente);
	}

	@Override
	public Paciente buscarPorId(Long id) {
		return pacienteRepository.findById(id).orElse(null);
	}
}
