package com.practica.clientes_api.controller;

import com.practica.clientes_api.model.Cliente;
import com.practica.clientes_api.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clientes")

public class ClienteController {
    
@Autowired
private ClienteService clienteService;
    

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
    return clienteService.buscarPorId(id);
}
    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {
    return clienteService.crear(cliente);
}
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminar (@PathVariable Long id){
    clienteService.eliminar(id);
    }
    
    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id,@RequestBody Cliente clienteActualizado){
     return clienteService.actualizar(id,clienteActualizado);   
    }
    
    
    @GetMapping
    public List<Cliente> listar(){
        return clienteService.listar();
    }
            
    
    
    
}
