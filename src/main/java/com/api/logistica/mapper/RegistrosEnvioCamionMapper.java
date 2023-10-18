package com.api.logistica.mapper;

import java.util.Date;

public class RegistrosEnvioCamionMapper {

    private String tipoProducto;
    private Date fechaRegistro;
    private Date fechaEntrega;
    private String bodegaEntrega;
    private String placaVehiculo;
    private String numeroGuia;

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getBodegaEntrega() {
        return bodegaEntrega;
    }

    public void setBodegaEntrega(String bodegaEntrega) {
        this.bodegaEntrega = bodegaEntrega;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

}
