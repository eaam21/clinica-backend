package com.examen.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.examen.model.Paciente;
import com.examen.repository.IPacienteRepository;

@RestController
@RequestMapping("/api/paciente")
@CrossOrigin(origins = "http://localhost:4200")
public class PacienteController {

    @Autowired
    private IPacienteRepository repopaciente;

    @GetMapping
    public List<Paciente> listar() {
        return repopaciente.findAll();
    }

    @GetMapping("/{id}")
    public Paciente buscar(@PathVariable int id) {
        return repopaciente.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<?> agregar(@Validated @RequestBody Paciente paciente) {
        double imc = calcularIMC(paciente.getPeso(), paciente.getTalla());
        paciente.setImc(imc);
        Paciente s = repopaciente.save(paciente);
        return ResponseEntity.ok(s);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable int id, @RequestBody Paciente paciente) {
        Paciente s = repopaciente.findById(id).orElse(null);
        if (s == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        s.setApellidopaterno(paciente.getApellidopaterno());
        s.setApellidomaterno(paciente.getApellidomaterno());
        s.setNombres(paciente.getNombres());
        s.setDni(paciente.getDni());
        s.setPeso(paciente.getPeso());
        s.setTalla(paciente.getTalla());
        double imc = calcularIMC(paciente.getPeso(), paciente.getTalla());
        s.setImc(imc);
        s.setId_consulta(paciente.getId_consulta());
        repopaciente.save(s);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
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
