package com.api.logistica.service;

import com.api.logistica.exceptions.EtAuthException;
import com.api.logistica.model.Usuario;
import com.api.logistica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario validateUser(String email, String password) throws EtAuthException{
        if(email != null) email = email.toLowerCase();
        return usuarioRepository.findByEmailAndPassword(email, password);
    }

    public Usuario registerUser(String username, String email, String password) throws EtAuthException{

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new EtAuthException("Formato de email invalido");
        Integer count = usuarioRepository.getCountByEmail(email);
        if(count > 0)
            throw new EtAuthException("Email ya existente");
        Integer idusuario = usuarioRepository.create(username, email, password);
        return usuarioRepository.findById(idusuario);
    }

}
