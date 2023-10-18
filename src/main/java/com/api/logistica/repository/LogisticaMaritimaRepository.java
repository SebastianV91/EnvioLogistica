package com.api.logistica.repository;

import com.api.logistica.dto.GuiaMaritimaDTO;
import com.api.logistica.mapper.RegistroLogisticaMaritimaMapper;
import com.api.logistica.model.LogisticaMaritima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.*;

@Component
public class LogisticaMaritimaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertEnvioNoGuiaRepository(LogisticaMaritima logisticaMaritima) {
        try {
            String sql = "INSERT INTO LOGISTICAMARITIMA"
                    + " (TIPOPRODUCTO, FECHAREGISTRO, FECHAENTREGA, PUERTOENTREGA, NUMEROFLOTA, NUMEROGUIA, CLIENTES_ID) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?) ";

            String numGuia = getRandomString(10);

            int rows = jdbcTemplate.update(sql, logisticaMaritima.getTipoProducto(), logisticaMaritima.getFechaRegistro(), logisticaMaritima.getFechaEntrega(), logisticaMaritima.getPuertoEntrega(), logisticaMaritima.getNumeroFlota(), numGuia, logisticaMaritima.getClienteId());

            return rows;

        } catch (Exception e) {
            throw e;
        }
    }

    public int updateEnvioNoGuiaRepository(LogisticaMaritima logisticaMaritima) {
        try {
            String sql = " UPDATE LOGISTICAMARITIMA "
                    + " SET TIPOPRODUCTO = ?, FECHAREGISTRO = ?, FECHAENTREGA =?, PUERTOENTREGA = ?, NUMEROFLOTA = ?   "
                    + " WHERE CLIENTES_ID = ? AND ID = ? ";

            int rows = jdbcTemplate.update(sql, logisticaMaritima.getTipoProducto(),
                    logisticaMaritima.getFechaRegistro(),
                    logisticaMaritima.getFechaEntrega(),
                    logisticaMaritima.getPuertoEntrega(),
                    logisticaMaritima.getNumeroFlota(),
                    logisticaMaritima.getClienteId(),
                    logisticaMaritima.getId());

            return rows;

        } catch (Exception e) {
            throw e;
        }
    }

    public Map<String, Object> selectRegistroEnvioRepository(GuiaMaritimaDTO guiaMaritimaDTO) {

        Map<String, Object> respuesta = new HashMap<>();
        List<RegistroLogisticaMaritimaMapper> listRegistroLogisticaMaritima = new ArrayList<>();

        String sql = "SELECT  "
                + " TIPOPRODUCTO, FECHAREGISTRO, FECHAENTREGA, PUERTOENTREGA, NUMEROFLOTA, NUMEROGUIA "
                + " FROM LOGISTICAMARITIMA "
                + " WHERE NUMEROGUIA = ? ";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, guiaMaritimaDTO.getNumeroGuia());

        if (rows != null) {

            for (Map<String, Object> row : rows) {
                RegistroLogisticaMaritimaMapper registroLogisticaMaritimaMapper = new RegistroLogisticaMaritimaMapper();
                registroLogisticaMaritimaMapper.setTipoProducto((String) row.get("TIPOPRODUCTO"));
                registroLogisticaMaritimaMapper.setFechaRegistro((Date) row.get("FECHAREGISTRO"));
                registroLogisticaMaritimaMapper.setFechaEntrega((Date) row.get("FECHAENTREGA"));
                registroLogisticaMaritimaMapper.setPuertoEntrega((String) row.get("PUERTOENTREGA"));
                registroLogisticaMaritimaMapper.setNumeroFlota((String) row.get("NUMEROFLOTA"));
                registroLogisticaMaritimaMapper.setNumeroGuia((String) row.get("NUMEROGUIA"));
                listRegistroLogisticaMaritima.add(registroLogisticaMaritimaMapper);
            }

            if(rows.isEmpty()){
                respuesta.put("message","Este registro no existe");
            }else{
                respuesta.put("registroEnvioMaritimo", listRegistroLogisticaMaritima);
            }

        }

        return respuesta;

    }

    public int deleteEnvioNoGuiaRepository(LogisticaMaritima logisticaMaritima){

        String sql = " DELETE FROM LOGISTICAMARITIMA "
                + " WHERE CLIENTES_ID = ? AND ID = ? ";

        int rows = jdbcTemplate.update(sql, logisticaMaritima.getClienteId(), logisticaMaritima.getId());

        return rows;

    }

    public String getRandomString(int i) {

        // bind the length
        byte[] bytearray;
        bytearray = new byte[256];
        String mystring;
        StringBuffer thebuffer;
        String theAlphaNumericS;

        new Random().nextBytes(bytearray);

        mystring = new String(bytearray, Charset.forName("UTF-8"));

        thebuffer = new StringBuffer();

        //remove all spacial char
        theAlphaNumericS = mystring.replaceAll("[^A-Z0-9]", "");

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
