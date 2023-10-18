package com.api.logistica.service;

import com.api.logistica.dto.NumDocumentoDTO;
import com.api.logistica.model.Clientes;
import com.api.logistica.repository.ClientesRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClientesService extends ClientesRepository {

    public Map<String, Object> insertClientes(Clientes clientes) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            if (insertClienteRepository(clientes) == 1) {
                response.put("mensaje", "Cliente registrado");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("ERROR EN PROCESOS BACKEND::" + e.getMessage());
            response.put("mensaje", "Error en el backend");
        }
        return response;
    }

    public Map<String, Object> updateClientes(Clientes clientes) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            if (updateClienteRepository(clientes) == 1) {
                response.put("mensaje", "Cliente modificado.");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("ERROR EN PROCESOS BACKEND::" + e.getMessage());
            response.put("mensaje", "Error en el backend");
        }
        return response;
    }

    public Map<String, Object> selectRegistroClienteIdentificacion(NumDocumentoDTO numDocumentoDTO){
        return selectRegistroClienteIdentificacionRepository(numDocumentoDTO);
    }

    public Map<String, Object> deleteClientes(Clientes clientes) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {

            if (deleteClienteRepository(clientes) == 1) {
                response.put("message", "Cliente eliminado");
            } else {
                response.put("message", "Este cliente no se puede eliminar porque no coincide con su usuario registrado.");
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("ERROR EN PROCESOS BACKEND::" + e.getMessage());
            response.put("mensaje", "Error en el backend");
        }
        return response;
    }

}
