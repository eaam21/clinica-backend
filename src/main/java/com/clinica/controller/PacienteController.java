package com.clinica.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.model.dto.PacienteInputDTO;
import com.clinica.model.entity.Paciente;
import com.clinica.model.service.IPacienteService;

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
    public ResponseEntity<Paciente> agregar(@RequestBody PacienteInputDTO pacienteDTO) {
        Paciente nuevoPaciente = pacienteService.registrar(pacienteDTO);
        return ResponseEntity.ok(nuevoPaciente);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody PacienteInputDTO pacienteDTO) {
        Paciente pacienteEncontrado = pacienteService.buscarPorId(id);
        if (pacienteEncontrado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Paciente pacienteActualizado = pacienteService.actualizar(id, pacienteDTO);
        return ResponseEntity.ok(pacienteActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Paciente>eliminar(@PathVariable Long id) {
        Paciente pacienteEncontrado = pacienteService.buscarPorId(id);
        if (pacienteEncontrado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Paciente pacienteEliminado = pacienteService.eliminar(id);
        return ResponseEntity.ok(pacienteEliminado);
    }
}
