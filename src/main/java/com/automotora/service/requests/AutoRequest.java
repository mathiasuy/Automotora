package com.automotora.service.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

public class AutoRequest extends VehiculoRequest{
    private int puertas;

    @JsonProperty("puertas")
    public int getPuertas() {
        return puertas;
    }

    @Override
    public String toString() {
        return super.toString()+
                "puertas=" + puertas +
                "} Auto";
    }
}
