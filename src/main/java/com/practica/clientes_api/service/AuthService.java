package com.practica.clientes_api.service;

import com.practica.clientes_api.model.Usuario;
import com.practica.clientes_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;
    
    public void validarUsuario(Usuario usuario){
     if(usuario.getUsername()== null || usuario.getUsername().isEmpty()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario vacio");
     }
     if(usuario.getPassword()== null || usuario.getPassword().isEmpty()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Contraseña vacia");
     } 
     if(usuario.getRol()== null || usuario.getRol().isEmpty()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Rol vacio");
     }
    }
    
    public Usuario registrarUsuario(Usuario usuario){
        
        validarUsuario(usuario);
        String passwordHasheada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordHasheada);
        return usuarioRepository.save(usuario);
    }
    
    public String login (String username, String password){
        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario o contraseña incorrectos"));
        
        boolean esValida = passwordEncoder.matches(password, usuario.getPassword());
        
        if(!esValida){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Usuario o contraseña incorrectos");
        }
        return jwtService.generarToken(usuario.getUsername(), usuario.getRol());
    }
}
   
       
