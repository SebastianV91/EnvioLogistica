package com.api.logistica.controller;

import com.api.logistica.Constants;
import com.api.logistica.model.Usuario;
import com.api.logistica.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        Usuario usuario = usuarioService.validateUser(email, password);
        return new ResponseEntity<>(generateJWTToken(usuario), HttpStatus.OK);
    }

    @PostMapping("/registro")
    public ResponseEntity<Map<String, String>> registroUsuario(@RequestBody Map<String, Object> userMap){
        String username = (String) userMap.get("username");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        Usuario usuario = usuarioService.registerUser(username, email, password);
        return new ResponseEntity<>(generateJWTToken(usuario), HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(Usuario usuario) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("id", usuario.getId())
                .claim("email", usuario.getEmail())
                .claim("username", usuario.getUsername())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

}
