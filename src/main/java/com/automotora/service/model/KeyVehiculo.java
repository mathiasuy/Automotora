package com.automotora.service.model;

public class KeyVehiculo {

    private String marca;
    private String modelo;

    public KeyVehiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
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
}
