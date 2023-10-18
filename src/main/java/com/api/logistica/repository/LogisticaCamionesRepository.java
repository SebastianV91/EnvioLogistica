package com.api.logistica.repository;

import com.api.logistica.dto.GuiaCamionDTO;
import com.api.logistica.mapper.RegistrosEnvioCamionMapper;
import com.api.logistica.model.Clientes;
import com.api.logistica.model.LogisticaCamiones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.*;

@Component
public class LogisticaCamionesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertEnvioNoGuiaRepository(LogisticaCamiones logisticaCamiones) {
        try {
            String sql = "INSERT INTO LOGISTICACAMIONES"
                    + " (TIPOPRODUCTO, FECHAREGISTRO, FECHAENVIO, BODEGAENTREGA, PLACAVEHICULO, NUMEROGUIA, CLIENTES_ID) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?) ";

            String numGuia = getRandomString(10);

            int rows = jdbcTemplate.update(sql, logisticaCamiones.getTipoProducto(), logisticaCamiones.getFechaRegistro(), logisticaCamiones.getFechaEnvio(), logisticaCamiones.getBodegaEntrega(), logisticaCamiones.getPlacaVehiculo(), numGuia, logisticaCamiones.getClienteId());

            return rows;

        } catch (Exception e) {
            throw e;
        }
    }

    public int updateEnvioNoGuiaRepository(LogisticaCamiones logisticaCamiones){
        try{
            String sql = " UPDATE LOGISTICACAMIONES "
                    + " SET TIPOPRODUCTO = ?, FECHAREGISTRO = ?, FECHAENVIO = ?, BODEGAENTREGA = ?, PLACAVEHICULO = ?   "
                    + " WHERE CLIENTES_ID = ? AND ID = ? ";

            int rows = jdbcTemplate.update(sql, logisticaCamiones.getTipoProducto(), logisticaCamiones.getFechaRegistro(), logisticaCamiones.getFechaEnvio(), logisticaCamiones.getBodegaEntrega(), logisticaCamiones.getPlacaVehiculo(), logisticaCamiones.getClienteId(), logisticaCamiones.getId());

            return rows;

        } catch (Exception e) {
            throw e;
        }
    }

    public Map<String, Object> selectRegistroEnvioRepository(GuiaCamionDTO guiaCamionDTO){

        Map<String, Object> respuesta = new HashMap<>();
        List<RegistrosEnvioCamionMapper> listRegistrosEnvioCamion = new ArrayList<>();

        String sql = "SELECT  "
                + " TIPOPRODUCTO, FECHAREGISTRO, FECHAENVIO, BODEGAENTREGA, PLACAVEHICULO, NUMEROGUIA "
                + " FROM LOGISTICACAMIONES "
                + " WHERE NUMEROGUIA = ? ";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, guiaCamionDTO.getNumeroGuia());

        if(rows != null){

            for(Map<String, Object> row: rows){
                RegistrosEnvioCamionMapper registrosEnvioCamionMapper = new RegistrosEnvioCamionMapper();
                registrosEnvioCamionMapper.setTipoProducto((String) row.get("TIPOPRODUCTO"));
                registrosEnvioCamionMapper.setFechaRegistro((Date) row.get("FECHAREGISTRO"));
                registrosEnvioCamionMapper.setFechaEnvio((Date) row.get("FECHAENVIO"));
                registrosEnvioCamionMapper.setBodegaEntrega((String) row.get("BODEGAENTREGA"));
                registrosEnvioCamionMapper.setPlacaVehiculo((String) row.get("PLACAVEHICULO"));
                registrosEnvioCamionMapper.setNumeroGuia((String) row.get("NUMEROGUIA"));
                listRegistrosEnvioCamion.add(registrosEnvioCamionMapper);
            }

            if(rows.isEmpty()){
                respuesta.put("message","Este registro no existe");
            }else{
                respuesta.put("registroEnvioCamion", listRegistrosEnvioCamion);
            }

        }

        return respuesta;
    }

    public int deleteEnvioNoGuiaRepository(LogisticaCamiones logisticaCamiones){

        String sql = " DELETE FROM LOGISTICACAMIONES "
                + " WHERE CLIENTES_ID = ? AND ID = ? ";

        int rows = jdbcTemplate.update(sql, logisticaCamiones.getClienteId(), logisticaCamiones.getId());

        return rows;

    }

    public String getRandomString(int i)
    {

        // bind the length
        byte[] bytearray;
        bytearray = new byte[256];
        String mystring;
        StringBuffer thebuffer;
        String theAlphaNumericS;

        new Random().nextBytes(bytearray);

        mystring
                = new String(bytearray, Charset.forName("UTF-8"));

        thebuffer = new StringBuffer();

        //remove all spacial char
        theAlphaNumericS
                = mystring
                .replaceAll("[^A-Z0-9]", "");

        //random selection
        for (int m = 0; m < theAlphaNumericS.length(); m++) {

            if (Character.isLetter(theAlphaNumericS.charAt(m))
                    && (i > 0)
                    || Character.isDigit(theAlphaNumericS.charAt(m))
                    && (i > 0)) {

                thebuffer.append(theAlphaNumericS.charAt(m));
                i--;
            }
        }

        // the resulting string
        return thebuffer.toString();
    }

}
