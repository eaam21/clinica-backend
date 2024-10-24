package com.clinica.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.model.Paciente;
import com.clinica.repository.IPacienteRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/paciente")
@AllArgsConstructor
public class PacienteController {

    private IPacienteRepository pacienteRepository;

    @GetMapping("/listar")
    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    @GetMapping("/obtener/{id}")
    public Paciente buscar(@PathVariable Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> agregar(@Validated @RequestBody Paciente paciente) {
        double imc = calcularIMC(paciente.getPeso(), paciente.getTalla());
        paciente.setImc(imc);
        Paciente s = pacienteRepository.save(paciente);
        return ResponseEntity.ok(s);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        Paciente pacienteEncontrado = pacienteRepository.findById(id).orElse(null);
        if (pacienteEncontrado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pacienteEncontrado.setApellidopaterno(paciente.getApellidopaterno());
        pacienteEncontrado.setApellidomaterno(paciente.getApellidomaterno());
        pacienteEncontrado.setNombres(paciente.getNombres());
        pacienteEncontrado.setDni(paciente.getDni());
        pacienteEncontrado.setPeso(paciente.getPeso());
        pacienteEncontrado.setTalla(paciente.getTalla());
        double imc = calcularIMC(paciente.getPeso(), paciente.getTalla());
        pacienteEncontrado.setImc(imc);
        pacienteEncontrado.setId(paciente.getId());
        pacienteRepository.save(pacienteEncontrado);
        return new ResponseEntity<>(pacienteEncontrado, HttpStatus.CREATED);
    }

    private double calcularIMC(int peso, int talla) {
        if (talla == 0) {
            throw new IllegalArgumentException("La talla no puede ser cero");
        }
        double imc = peso / Math.pow(talla / 100.0, 2);
        BigDecimal imcRounded = new BigDecimal(imc, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
        return imcRounded.doubleValue();
    }
}
