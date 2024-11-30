package com.clinica.model.service;

import com.clinica.model.dto.LoginInputDTO;
import com.clinica.model.dto.LoginOutputDTO;

public interface IUsuarioService {
	LoginOutputDTO login (LoginInputDTO inputDTO);
}
