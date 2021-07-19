package com.vendaprodutos.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendaprodutos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);

}
