package com.clinica.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clinica.model.entity.Rol;
import com.clinica.model.entity.Usuario;
import com.clinica.model.repository.IUsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {	
	private IUsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findOneByNombreUsuario(username).orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"));
		ArrayList<Rol> listaRoles = new ArrayList<Rol>(usuario.getRoles());
		return User
				.withUsername(usuario.getNombreUsuario())
				.password(usuario.getClaveUsuario())
				.roles(listaRoles.get(0).getNombre())
				.build();
	}
}
