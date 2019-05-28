package com.automotora.service.model;

import com.automotora.service.responses.VehiculoResponse;

public abstract class Vehiculo {

    private String modelo;
    private String marca;
    private String descripcion;

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getMarca().hashCode();
        result = prime * result + getModelo().hashCode();
        return result;
    }

    public String getDescripcion(){
        return this.descripcion;
    };

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof KeyVehiculo)){
            return false;
        }
        KeyVehiculo key = (KeyVehiculo)obj;
        if (key.getMarca().equals(marca) && key.getModelo().equals(modelo)){
            return true;
        }
        return false;
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

