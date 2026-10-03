package com.practica.clientes_api.service;
import com.practica.clientes_api.model.Cliente;
import com.practica.clientes_api.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    public boolean existePorEmail(String email){
       return clienteRepository.existsByEmail(email);
    }
    
    public void validarCliente(Cliente cliente){
        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No existe nombre");
        }
        
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No existe email");
        }
        
        if (!cliente.getEmail().contains("@")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Correo no tiene @");
        }
        
    }
    
    public Cliente crear (Cliente cliente){
        
         validarCliente(cliente);
         
         return clienteRepository.save(cliente);
    }
    
    public Cliente buscarPorId(Long id){
        return clienteRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe el cliente"));
    }
    
    public void eliminar (Long id){
        
        if(!clienteRepository.existsById(id)){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente no encontrado");

           }
         clienteRepository.deleteById(id);
    }   
    
    public Cliente actualizar(Long id, Cliente clienteActualizado){
       
        Cliente clienteActual = buscarPorId(id);
       
        validarCliente(clienteActualizado);
        
        clienteActual.setNombre(clienteActualizado.getNombre());
        clienteActual.setEmail(clienteActualizado.getEmail());
        
               
        return clienteRepository.save(clienteActual);       
    }
    
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }
    
}
