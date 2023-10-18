package com.api.logistica.repository;

import com.api.logistica.exceptions.EtAuthException;
import com.api.logistica.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UsuarioRepository {

    private static final String SQL_CREATE = "INSERT INTO USUARIOS (USERNAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";
    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM USUARIOS WHERE EMAIL = ?";
    private static final String SQL_FIND_BY_ID = "SELECT ID, USERNAME, EMAIL, PASSWORD FROM USUARIOS" +
            " WHERE ID = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT ID, USERNAME, EMAIL, PASSWORD FROM USUARIOS" +
            " WHERE EMAIL = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer create(String username, String email, String password) throws EtAuthException {
        try{
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

        int rows = jdbcTemplate.update(SQL_CREATE, username, email, hashedPassword);

        return rows;
        } catch(Exception e){
            throw new EtAuthException("Detalles Invalidos. Fallido a crear cuenta");
        }

    }

    public Usuario findByEmailAndPassword(String email, String password) throws EtAuthException {
        try {
            Usuario usuario = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, usuarioRowMapper);
            if (!BCrypt.checkpw(password, usuario.getPassword()))
                throw new EtAuthException("Password o email invalidos");
            return usuario;
        } catch (EmptyResultDataAccessException e) {
            throw new EtAuthException("Password o email invalidos");
        }
    }

    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);
    }

    public Usuario findById(Integer id) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, usuarioRowMapper);
    }

    private RowMapper<Usuario> usuarioRowMapper = ((rs, rowNum) -> {
        return new Usuario(rs.getInt("ID"),
                rs.getString("USERNAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"));
    });

}
