package com.api.logistica.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UsuarioRepository {

    private static final String SQL_CREATE = "INSERT INTO USUARIOS (USERNAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer create(String username, String email, String password){

        try{
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            return ps;
        }, keyHolder);
        return (Integer) keyHolder.getKeys().get("ID");
        }catch(Exception e){
            throw e;
        }
    }

    

}
