package com.clinica.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.model.Paciente;
import com.clinica.model.dto.PacienteInputDTO;
import com.clinica.model.service.IPacienteService;
import com.clinica.repository.IPacienteRepository;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/paciente")
@AllArgsConstructor
public class PacienteController {

    private IPacienteService pacienteService;

    @GetMapping("/listar")
    public List<Paciente> listar() {
        return pacienteService.listar();
    }

    @GetMapping("/obtener/{id}")
    public Paciente buscar(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> agregar(@Validated @RequestBody PacienteInputDTO pacienteDTO) {
        Paciente s = pacienteService.registrar(pacienteDTO);
        return ResponseEntity.ok(s);
    }

    /*
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody PacienteInputDTO pacienteDTO) {
        Paciente pacienteEncontrado = pacienteService.buscarPorId(id);
        if (pacienteEncontrado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pacienteEncontrado.setApellidoPaterno(pacienteDTO.getApellidoPaterno());
        pacienteEncontrado.setApellidoMaterno(paciente.getApellidoMaterno());
        pacienteEncontrado.setNombres(paciente.getNombres());
        pacienteEncontrado.setDni(paciente.getDni());
        pacienteEncontrado.setPeso(paciente.getPeso());
        pacienteEncontrado.setTalla(paciente.getTalla());
        //double imc = calcularIMC(paciente.getPeso(), paciente.getTalla());
        pacienteEncontrado.setImc(imc);
        pacienteEncontrado.setId(paciente.getId());
        //pacienteRepository.save(pacienteEncontrado);
        return new ResponseEntity<>(pacienteEncontrado, HttpStatus.CREATED);
    }
    */
}
