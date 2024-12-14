package com.clinica.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	private JwtFilter jwtFilter;
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.csrf(csrf->csrf.disable())
    		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    		.authorizeHttpRequests(http->{
                http.requestMatchers("/api/usuario/login").permitAll();
                http.requestMatchers("/api/paciente/listar").hasAnyRole("ADMIN", "MEDICO", "ASISTENTE");
                http.requestMatchers("/api/especialidad/listar").hasAnyRole("ADMIN", "MEDICO");
                http.requestMatchers("/api/paciente/obtener/**").hasAnyRole("ADMIN", "MEDICO");
                http.requestMatchers("/api/paciente/registrar").hasAnyRole("ADMIN", "MEDICO");
                http.requestMatchers("/api/paciente/actualizar/**").hasAnyRole("ADMIN", "MEDICO");
                http.requestMatchers("/api/paciente/eliminar").hasRole("ADMIN");
                http.anyRequest().authenticated();
    		})
    		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    	return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
