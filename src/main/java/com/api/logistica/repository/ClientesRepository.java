package com.api.logistica.repository;

import com.api.logistica.dto.NumDocumentoDTO;
import com.api.logistica.mapper.RegistroClienteMapper;
import com.api.logistica.model.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ClientesRepository {

    private static final String SQL_UPDATE = "UPDATE CLIENTES SET PRIMERNOMBRE = ?, SEGUNDONOMBRE = ?, PRIMERAPELLIDO = ?, SEGUNDOAPELLIDO = ?, DIRECCION = ?, CELULAR = ? " +
            "WHERE USUARIOS_ID = ? AND ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertClienteRepository(Clientes clientes) {
        try {
            String sql = "INSERT INTO CLIENTES"
                    + " (PRIMERNOMBRE, SEGUNDONOMBRE, PRIMERAPELLIDO, SEGUNDOAPELLIDO, NUMERODOCUMENTO, DIRECCION, CELULAR, USUARIOS_ID) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?) ";

            int rows = jdbcTemplate.update(sql, clientes.getPrimerNombre(), clientes.getSegundoNombre(), clientes.getPrimerApellido(), clientes.getSegundoApellido(), clientes.getNumeroDocumento(), clientes.getDireccion(), clientes.getCelular(), clientes.getUsuarios_id());

            return rows;
        } catch (Exception e) {
            throw e;
        }
    }

    public int updateClienteRepository(Clientes clientes) {
        try {
            String sql = " UPDATE CLIENTES "
                    + " SET PRIMERNOMBRE = ?, SEGUNDONOMBRE = ?, PRIMERAPELLIDO = ?, SEGUNDOAPELLIDO = ?, DIRECCION = ?, CELULAR = ?   "
                    + " WHERE USUARIOS_ID = ? AND ID = ? ";

            int rows = jdbcTemplate.update(sql, clientes.getPrimerNombre(), clientes.getSegundoNombre(), clientes.getPrimerApellido(), clientes.getSegundoApellido(), clientes.getDireccion(), clientes.getCelular(), clientes.getUsuarios_id(), clientes.getId());

            return rows;

        } catch (Exception e) {
            throw e;
        }
    }

    public Map<String, Object> selectRegistroClienteIdentificacionRepository(NumDocumentoDTO numDocumentoDTO){

        Map<String, Object> respuesta = new HashMap<>();
        List<RegistroClienteMapper> listRegistroCliente = new ArrayList<>();

        String sql = "SELECT  "
                + " PRIMERNOMBRE, SEGUNDONOMBRE, PRIMERAPELLIDO, SEGUNDOAPELLIDO, NUMERODOCUMENTO, DIRECCION, CELULAR "
                + " FROM CLIENTES "
                + " WHERE NUMERODOCUMENTO = ? ";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, numDocumentoDTO.getNumDocumento());

        if(rows != null){
            for(Map<String, Object> row: rows){
                RegistroClienteMapper registroClienteMapper = new RegistroClienteMapper();
                registroClienteMapper.setPrimerNombre((String) row.get("PRIMERNOMBRE"));
                registroClienteMapper.setSegundoNombre((String) row.get("SEGUNDONOMBRE"));
                registroClienteMapper.setPrimerApellido((String) row.get("PRIMERAPELLIDO"));
                registroClienteMapper.setSegundoApellido((String) row.get("SEGUNDOAPELLIDO"));
                registroClienteMapper.setNumeroDocumento((String) row.get("NUMERODOCUMENTO"));
                registroClienteMapper.setDireccion((String) row.get("DIRECCION"));
                registroClienteMapper.setCelular((String) row.get("CELULAR"));
                listRegistroCliente.add(registroClienteMapper);
            }

            if(rows.isEmpty()){
                respuesta.put("message", "Este registro no existe");
            }else{
                respuesta.put("registroCliente", listRegistroCliente);
            }

        }

        return respuesta;
    }

    public int deleteClienteRepository(Clientes clientes) {

        String sql = " DELETE FROM CLIENTES "
                + "WHERE USUARIOS_ID = ? AND ID = ? ";

        int rows = jdbcTemplate.update(sql, clientes.getUsuarios_id(), clientes.getId());

        return rows;

    }

}
