package com.automotora.service.responses;

public class MotoResponse extends VehiculoResponse {

    public MotoResponse(String modelo, String marca, String descripcion) {
        super(modelo, marca,descripcion);
    }

    @Override
    public String toString() {
        return super.toString()
                + "} Moto.";
    }
}
