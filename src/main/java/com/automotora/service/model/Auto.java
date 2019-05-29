package com.automotora.service.model;


import com.automotora.service.responses.AutoResponse;
import com.automotora.service.responses.VehiculoResponse;

public class Auto extends Vehiculo{

    private int puertas;

    public Auto(String marca, String modelo) {
        super(marca, modelo);
    }

    public Auto(String marca, String modelo, int puertas) {
        super(marca, modelo);
        this.puertas = puertas;
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    @Override
    public VehiculoResponse getResponse(){
        return new AutoResponse(getMarca(),getModelo(),puertas,getDescripcion());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Auto)){
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
