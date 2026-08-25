package com.finanlice.repository;

import com.finanlice.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <UsuarioModel , Long>{
}
