package com.automotora.service.model;

public class KeyVehiculo {

    private String marca;
    private String modelo;

    public KeyVehiculo(Vehiculo vehiculo) {
        this.marca = vehiculo.getMarca();
        this.modelo = vehiculo.getModelo();
    }

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
        final int prime = 31;
        int result = 1;
        result = prime * result + getMarca().hashCode();
        result = prime * result + getModelo().hashCode();
        return result;
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
