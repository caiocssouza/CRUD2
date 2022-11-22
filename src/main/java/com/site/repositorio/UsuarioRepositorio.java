package com.site.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.site.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
