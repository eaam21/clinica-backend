package com.clinica.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.model.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

}
