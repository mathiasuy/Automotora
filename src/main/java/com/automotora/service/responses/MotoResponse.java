package com.automotora.service.responses;

public class MotoResponse extends VehiculoResponse {

    public MotoResponse(String modelo, String marca) {
        super(modelo, marca);
    }

    @Override
    public String toString() {
        return super.toString()
                + "} Moto.";
    }
}
