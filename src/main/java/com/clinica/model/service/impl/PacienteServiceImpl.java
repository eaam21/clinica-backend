package com.clinica.model.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinica.model.dto.PacienteInputDTO;
import com.clinica.model.entity.Especialidad;
import com.clinica.model.entity.Paciente;
import com.clinica.model.repository.IPacienteRepository;
import com.clinica.model.service.IPacienteService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PacienteServiceImpl implements IPacienteService {

	private IPacienteRepository pacienteRepository;
	
	@Override
	public List<Paciente> listar() {
		Integer estadoActivo = 1;
		return pacienteRepository.findAllByEstado(estadoActivo);
	}
	
	@Override
	@Transactional
	public Paciente registrar(PacienteInputDTO pacienteDTO) {
		Integer estadoActivo = 1;
		Paciente paciente = new Paciente();
		paciente.setApellidoPaterno(pacienteDTO.apellidoPaterno());
		paciente.setApellidoMaterno(pacienteDTO.apellidoMaterno());
		paciente.setNombres(pacienteDTO.nombres());
		paciente.setDni(pacienteDTO.dni());
		paciente.setPeso(pacienteDTO.peso());
		paciente.setTalla(pacienteDTO.talla());
		paciente.setImc(pacienteDTO.imc());
		paciente.setEstado(estadoActivo);
		Especialidad especialidad = new Especialidad();
		especialidad.setId(pacienteDTO.idEspecialidad());
		paciente.setEspecialidad(especialidad);
		return pacienteRepository.save(paciente);
	}

	@Override
	public Paciente buscarPorId(Long id) {
		return pacienteRepository.findById(id).orElse(null);
	}

	@Override
	public Paciente actualizar(Long idPaciente, PacienteInputDTO pacienteDTO) {
		Paciente pacienteEncontrado = buscarPorId(idPaciente);		
        pacienteEncontrado.setApellidoPaterno(pacienteDTO.apellidoPaterno());
        pacienteEncontrado.setApellidoMaterno(pacienteDTO.apellidoMaterno());
        pacienteEncontrado.setNombres(pacienteDTO.nombres());
        pacienteEncontrado.setDni(pacienteDTO.dni());
        pacienteEncontrado.setPeso(pacienteDTO.peso());
        pacienteEncontrado.setTalla(pacienteDTO.talla());
        pacienteEncontrado.setImc(pacienteDTO.imc());
		Especialidad especialidad = new Especialidad();
		especialidad.setId(pacienteDTO.idEspecialidad());
        pacienteEncontrado.setEspecialidad(especialidad);
		return pacienteRepository.save(pacienteEncontrado);
	}

	@Override
	public Paciente eliminar(Long idPaciente) {
		Integer estadoInactivo = 0;
		Paciente pacienteEncontrado = buscarPorId(idPaciente);
		pacienteEncontrado.setEstado(estadoInactivo);
		return pacienteRepository.save(pacienteEncontrado);
	}
}
