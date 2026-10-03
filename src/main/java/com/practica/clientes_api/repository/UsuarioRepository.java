package com.practica.clientes_api.repository;

import com.practica.clientes_api.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    
    Optional<Usuario> findByUsername(String username);

}
