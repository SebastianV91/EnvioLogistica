package com.api.logistica.service;

import com.api.logistica.dto.GuiaCamionDTO;
import com.api.logistica.model.LogisticaCamiones;
import com.api.logistica.repository.LogisticaCamionesRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LogisticaCamionesService extends LogisticaCamionesRepository {

    public Map<String, Object> insertEnvioNoGuia(LogisticaCamiones logisticaCamiones) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            if (insertEnvioNoGuiaRepository(logisticaCamiones) == 1) {
                response.put("mensaje", "Envio por camion registrado correctamente");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("ERROR EN PROCESOS BACKEND::" + e.getMessage());
            response.put("mensaje", "Error en el backend");
        }
        return response;
    }

    public Map<String, Object> updateEnvioNoGuia(LogisticaCamiones logisticaCamiones){
        Map<String, Object> response = new HashMap<String, Object>();
        try{

            if(updateEnvioNoGuiaRepository(logisticaCamiones) == 1){
                response.put("mensaje", "Envio por camion modificado correctamente");
            }

        }catch(Exception e){
            // TODO: handle exception
            System.out.println("ERROR EN PROCESOS BACKEND::" + e.getMessage());
            response.put("mensaje", "Error en el backend");
        }
        return response;
    }

    public Map<String, Object> selectRegistroEnvio(GuiaCamionDTO guiaCamionDTO){
        return selectRegistroEnvioRepository(guiaCamionDTO);
    }

    public Map<String, Object> deleteEnvioNoGuia(LogisticaCamiones logisticaCamiones){
        Map<String, Object> response = new HashMap<String, Object>();
        try{

            if(deleteEnvioNoGuiaRepository(logisticaCamiones) == 1){
                response.put("message", "Envio de logistica por camion eliminado correctamente.");
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
