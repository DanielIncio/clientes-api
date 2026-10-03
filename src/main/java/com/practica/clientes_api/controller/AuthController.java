package com.practica.clientes_api.controller;

import com.practica.clientes_api.model.Usuario;
import com.practica.clientes_api.service.AuthService;
import com.practica.clientes_api.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")

public class AuthController {

@Autowired
private AuthService authService;   

@PostMapping("/registro")
public Usuario registrar(@RequestBody Usuario usuario){
    return authService.registrarUsuario(usuario);
}

@PostMapping("/login")
public String login (@RequestBody Usuario usuario){
    return authService.login(usuario.getUsername(),usuario.getPassword());
}   


}
