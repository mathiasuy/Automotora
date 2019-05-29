package com.automotora.service.responses;

public class MotoResponse extends VehiculoResponse {

    public MotoResponse(String modelo, String marca, String descripcion) {
        super(modelo, marca,descripcion);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MotoResponse)){
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString()
                + "} Moto.";
    }
}
