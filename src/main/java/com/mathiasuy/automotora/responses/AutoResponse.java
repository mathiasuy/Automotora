package com.mathiasuy.automotora.responses;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class AutoResponse extends VehiculoResponse{
    private int puertas;
    
}
