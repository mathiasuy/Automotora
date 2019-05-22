package com.automotora.service.requests;

public class MotoRequest extends VehiculoRequest {
    public MotoRequest(String marca, String modelo) {
        super(marca, modelo);
    }

    @Override
    public String toString() {
        return  super.toString()+
                "} Moto";
    }
}
