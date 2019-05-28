package com.automotora.service.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class VehiculoResponse {
    private String marca;
    private String modelo;
    private String info;
    private String descripcion;

    public VehiculoResponse(String marca, String modelo, String descripcion) {
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
    }

    public void setInfo(String info){
        this.info = info;
    }

    @JsonProperty("info")
    public String getInfo() {
        return info;
    }

    @JsonProperty("marca")
    public String getMarca() {
            return marca;
    }

    @JsonProperty("model")
    public String getModelo() {
            return modelo;
    }

    @JsonProperty("descripcion")
    public String getDescripcion(){
        return descripcion;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
            "model='" + modelo + '\'' +
            ", marca='" + marca + '\''+
                ", info='" + info + '\''
            ;
    }
}
