package com.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examen.model.Paciente;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {

}
