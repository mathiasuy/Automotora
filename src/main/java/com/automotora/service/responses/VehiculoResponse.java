package com.automotora.service.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class VehiculoResponse {
    private String marca;
    private String modelo;
    private String info;

    public VehiculoResponse(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
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

    @Override
    public String toString() {
        return "Vehiculo{" +
            "model='" + modelo + '\'' +
            ", marca='" + marca + '\''+
                ", info='" + info + '\''
            ;
    }
}
