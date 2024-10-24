package com.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examen.model.Consulta;

public interface IConsultaRepository extends JpaRepository<Consulta, Integer> {

}
