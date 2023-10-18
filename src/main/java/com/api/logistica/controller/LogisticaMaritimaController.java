package com.api.logistica.controller;

import com.api.logistica.dto.GuiaMaritimaDTO;
import com.api.logistica.model.LogisticaMaritima;
import com.api.logistica.service.LogisticaMaritimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logisticaMaritima/")
public class LogisticaMaritimaController {

    @Autowired
    LogisticaMaritimaService logisticaMaritimaService;

    @PostMapping("/guardarEnvioMaritimo")
    public ResponseEntity<?> registrarEnvioMaritimo(@RequestBody LogisticaMaritima logisticaMaritima){
        return new ResponseEntity(logisticaMaritimaService.insertEnvioNoGuia(logisticaMaritima), HttpStatus.OK);
    }

    @PostMapping("/modificarEnvioMaritimo")
    public ResponseEntity<?> modificarEnvioMaritimo(@RequestBody LogisticaMaritima logisticaMaritima){
        return new ResponseEntity(logisticaMaritimaService.updateEnvioNoGuia(logisticaMaritima), HttpStatus.OK);
    }

    @PostMapping("/consultaEnvioMaritimo")
    public ResponseEntity<?> consultaEnvioMaritimo(@RequestBody GuiaMaritimaDTO guiaMaritimaDTO){
        return new ResponseEntity(logisticaMaritimaService.selectRegistroEnvio(guiaMaritimaDTO), HttpStatus.OK);
    }

    @PostMapping("/eliminacionEnvioMaritimo")
    public ResponseEntity<?> eliminacionEnvioCamion(@RequestBody LogisticaMaritima logisticaMaritima){
        return new ResponseEntity(logisticaMaritimaService.deleteEnvioNoGuia(logisticaMaritima), HttpStatus.OK);
    }

}
