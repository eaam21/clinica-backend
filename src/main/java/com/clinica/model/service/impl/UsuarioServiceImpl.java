package com.clinica.model.service.impl;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.clinica.model.dto.LoginInputDTO;
import com.clinica.model.dto.LoginOutputDTO;
import com.clinica.model.entity.Rol;
import com.clinica.model.entity.Usuario;
import com.clinica.model.repository.IUsuarioRepository;
import com.clinica.model.service.IUsuarioService;
import com.clinica.security.JwtUtil;
import com.clinica.util.BCryptUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;
	private IUsuarioRepository usuarioRepository;
	private BCryptUtil bcryptUtil;
	
	@Override
	public LoginOutputDTO login(LoginInputDTO inputDTO) {

		Usuario usuario = usuarioRepository.findOneByNombreUsuario(inputDTO.usuario()).orElse(null);
		if(usuario!=null) {
			if(bcryptUtil.validarPassword(inputDTO.clave(), usuario.getClaveUsuario())){
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(inputDTO.usuario(), inputDTO.clave())
						);
				ArrayList<Rol> listaRoles = new ArrayList<Rol>(usuario.getRoles());
				if(authentication.isAuthenticated()) {
					String token = jwtUtil.generateToken(usuario.getNombreUsuario(), listaRoles.get(0).getNombre());
					return new LoginOutputDTO(Boolean.TRUE, "Operación realizada correctamente", token);
				}else {
					return new LoginOutputDTO(Boolean.FALSE, "Usuario y/o contraseña invalidos", null);
				}
			}else {
				return new LoginOutputDTO(Boolean.FALSE, "Usuario y/o contraseña invalidos", null);
			}
		}else {
			return new LoginOutputDTO(Boolean.FALSE, "Usuario y/o contraseña invalidos", null);
		}
	}
}
