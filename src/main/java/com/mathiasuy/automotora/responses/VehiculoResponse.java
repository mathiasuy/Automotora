package com.automotora.responses;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class VehiculoResponse {
	private Long id;
    private String marca;
    private String modelo;
    private String descripcion;
}
