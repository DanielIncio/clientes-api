package com.practica.clientes_api.repository;
import com.practica.clientes_api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    boolean existsByEmail(String email);
}
