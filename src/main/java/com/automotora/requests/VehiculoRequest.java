package com.automotora.requests;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VehiculoRequest {
    private String marca;
    private String modelo;
    private String descripcion;

}
