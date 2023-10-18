package com.api.logistica.service;

import com.api.logistica.dto.GuiaMaritimaDTO;
import com.api.logistica.model.LogisticaMaritima;
import com.api.logistica.repository.LogisticaMaritimaRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LogisticaMaritimaService extends LogisticaMaritimaRepository {

    public Map<String, Object> insertEnvioNoGuia(LogisticaMaritima logisticaMaritima) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {

            if (insertEnvioNoGuiaRepository(logisticaMaritima) == 1) {
                response.put("mensaje", "Envio maritimo registrado correctamente");
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("ERROR EN PROCESOS BACKEND::" + e.getMessage());
            response.put("mensaje", "Error en el backend");
        }
        return response;
    }

    public Map<String, Object> updateEnvioNoGuia(LogisticaMaritima logisticaMaritima){
        Map<String, Object> response = new HashMap<String, Object>();
        try{

            if(updateEnvioNoGuiaRepository(logisticaMaritima) == 1){
                response.put("mensaje", "Envio maritimo modificado correctamente");
            }

        }catch(Exception e){
            // TODO: handle exception
            System.out.println("ERROR EN PROCESOS BACKEND::" + e.getMessage());
            response.put("mensaje", "Error en el backend");
        }
        return response;
    }

    public Map<String, Object> selectRegistroEnvio(GuiaMaritimaDTO guiaMaritimaDTO){
        return selectRegistroEnvioRepository(guiaMaritimaDTO);
    }

    public Map<String, Object> deleteEnvioNoGuia(LogisticaMaritima logisticaMaritima){
        Map<String, Object> response = new HashMap<String, Object>();
        try{

            if(deleteEnvioNoGuiaRepository(logisticaMaritima) == 1){
                response.put("message", "Envio de logistica por medio maritimo eliminado correctamente.");
            }else{
                response.put("message", "Este envio no se puede eliminar porque no coincide con el cliente registrado.");
            }

        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("ERROR EN PROCESOS BACKEND::" + e.getMessage());
            response.put("mensaje", "Error en el backend");
        }

        return response;
    }

}
