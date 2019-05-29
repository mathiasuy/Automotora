package com.automotora.service.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AutoResponse extends VehiculoResponse{
    private int puertas;

    public AutoResponse(String marca, String modelo, int puertas, String descripcion) {
        super(marca, modelo, descripcion);
        this.puertas = puertas;
    }

    @JsonProperty("puertas")
    public int getPuertas() {
        return puertas;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AutoResponse)){
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString() +
                "puertas=" + puertas +
                "} Auto.";
    }

}
