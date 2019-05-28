package com.automotora.service.model;

import com.automotora.service.responses.VehiculoResponse;

public abstract class Vehiculo {

    private String modelo;
    private String marca;

    public Vehiculo(String marca, String modelo) {
        this.modelo = modelo;
        this.marca = marca;
    }


    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "model='" + modelo + '\'' +
                ", marca='" + marca + '\''
                ;
    }

    public abstract VehiculoResponse getResponse();
}

