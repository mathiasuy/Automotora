package com.automotora.service.model;

import com.automotora.service.responses.MotoResponse;
import com.automotora.service.responses.VehiculoResponse;

public class Moto extends Vehiculo{

    public Moto(String marca, String modelo) {
        super(marca, modelo);
    }

    @Override
    public String toString() {
        return super.toString()
                + "} Moto.";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Moto)){
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public VehiculoResponse getResponse() {
        return new MotoResponse(getMarca(),getModelo(),getDescripcion());
    }
}
