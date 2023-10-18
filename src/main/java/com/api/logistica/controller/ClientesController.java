package com.api.logistica.controller;

import com.api.logistica.dto.NumDocumentoDTO;
import com.api.logistica.model.Clientes;
import com.api.logistica.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes/")
public class ClientesController {

    @Autowired
    ClientesService clientesService;

    @PostMapping("/guardarCliente")
    public ResponseEntity<?> registrarCliente(@RequestBody Clientes clientes){
        return new ResponseEntity(clientesService.insertClientes(clientes), HttpStatus.OK);
    }

    @PostMapping("/actualizarCliente")
    public ResponseEntity<?> actualizarCliente(@RequestBody Clientes clientes){
        return new ResponseEntity(clientesService.updateClientes(clientes), HttpStatus.OK);
    }

    @PostMapping("/eliminarCliente")
    public ResponseEntity<?> eliminarCliente(@RequestBody Clientes clientes){
        return new ResponseEntity(clientesService.deleteClientes(clientes), HttpStatus.OK);
    }

    @PostMapping("/consultaCliente")
    public ResponseEntity<?> consultaRegistroCliente(@RequestBody NumDocumentoDTO numeroDocumentoDTO){
        return new ResponseEntity(clientesService.selectRegistroClienteIdentificacion(numeroDocumentoDTO), HttpStatus.OK);
    }

}
