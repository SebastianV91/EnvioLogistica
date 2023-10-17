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
            "WHERE ID = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT ID, USERNAME, EMAIL, PASSWORD FROM USUARIOS" +
            "WHERE EMAIL = ? AND PASSWORD = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer create(String username, String email, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, password);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ID");
        } catch (Exception e) {
            throw e;
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
