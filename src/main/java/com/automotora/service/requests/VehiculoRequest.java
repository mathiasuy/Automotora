package com.automotora.service.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehiculoRequest {
    private String marca;
    private String modelo;

    public VehiculoRequest(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    @JsonProperty("marca")
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @JsonProperty("model")
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Vehiculo {" +
                "marca='" + marca + '\'' +
                ", model='" + modelo + '\''
                ;
    }
}
