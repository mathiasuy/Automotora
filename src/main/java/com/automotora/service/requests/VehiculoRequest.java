package com.automotora.service.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehiculoRequest {
    private String marca;
    private String modelo;

    @JsonProperty("marca")
    public String getMarca() {
        return marca;
    }

    @JsonProperty("modelo")
    public String getModelo() {
        return modelo;
    }

    @Override
    public String toString() {
        return "Vehiculo {" +
                "marca='" + marca + '\'' +
                ", model='" + modelo + '\''
                ;
    }
}
