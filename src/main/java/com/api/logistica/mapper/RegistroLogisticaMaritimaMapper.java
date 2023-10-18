package com.api.logistica.mapper;

import java.util.Date;

public class RegistroLogisticaMaritimaMapper {

    private String tipoProducto;
    private Date fechaRegistro;
    private Date fechaEntrega;
    private String puertoEntrega;
    private String numeroFlota;
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

    public String getPuertoEntrega() {
        return puertoEntrega;
    }

    public void setPuertoEntrega(String puertoEntrega) {
        this.puertoEntrega = puertoEntrega;
    }

    public String getNumeroFlota() {
        return numeroFlota;
    }

    public void setNumeroFlota(String numeroFlota) {
        this.numeroFlota = numeroFlota;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }
}
