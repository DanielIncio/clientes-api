package com.practica.clientes_api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generarToken(String username, String rol) {
        long ahora = System.currentTimeMillis();
        long expiracion = ahora + (1000 * 60 * 60); // 1 hora

        return Jwts.builder()
                .setSubject(username)
                .claim("rol", rol)
                .setIssuedAt(new Date(ahora))
                .setExpiration(new Date(expiracion))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    private Claims extraerClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
    public String extraerUsername(String token) {
        return extraerClaims(token).getSubject();
    }
    
    public String extraerRol(String token){
        return extraerClaims(token).get("rol",String.class);
    }
    
    public boolean esTokenValido(String token, String username) {
    final String usernameDelToken = extraerUsername(token);
    return (usernameDelToken.equals(username) && !esTokenExpirado(token));
    }

    private boolean esTokenExpirado(String token) {
    return extraerClaims(token).getExpiration().before(new Date());
    }
    
    
}
