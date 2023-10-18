package com.api.logistica.controller;

import com.api.logistica.dto.GuiaCamionDTO;
import com.api.logistica.model.LogisticaCamiones;
import com.api.logistica.service.LogisticaCamionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logisticaCamiones/")
public class LogisticaCamionesController {

    @Autowired
    LogisticaCamionesService logisticaCamionesService;

    @PostMapping("/guardarEnvioCamion")
    public ResponseEntity<?> registrarEnvioCamion(@RequestBody LogisticaCamiones logisticaCamiones){
        return new ResponseEntity(logisticaCamionesService.insertEnvioNoGuia(logisticaCamiones), HttpStatus.OK);
    }

    @PostMapping("/modificarEnvioCamion")
    public ResponseEntity<?> actualizarEnvioCamion(@RequestBody LogisticaCamiones logisticaCamiones){
        return new ResponseEntity(logisticaCamionesService.updateEnvioNoGuia(logisticaCamiones), HttpStatus.OK);
    }

    @PostMapping("/consultaEnvioCamion")
    public ResponseEntity<?> consultaEnvioCamion(@RequestBody GuiaCamionDTO guiaCamionDTO){
        return new ResponseEntity(logisticaCamionesService.selectRegistroEnvio(guiaCamionDTO), HttpStatus.OK);
    }

    @PostMapping("/eliminacionEnvioCamion")
    public ResponseEntity<?> eliminacionEnvioCamion(@RequestBody LogisticaCamiones logisticaCamiones){
        return new ResponseEntity(logisticaCamionesService.deleteEnvioNoGuia(logisticaCamiones), HttpStatus.OK);
    }

}
