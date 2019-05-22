package com.automotora.service.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AutoRequest extends VehiculoRequest{
    private int puertas;

    public AutoRequest(String marca, String modelo, int puertas) {
        super(marca, modelo);
        this.puertas = puertas;
    }

    @JsonProperty("puertas")
    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    @Override
    public String toString() {
        return super.toString()+
                "puertas=" + puertas +
                "} Auto";
    }
}
