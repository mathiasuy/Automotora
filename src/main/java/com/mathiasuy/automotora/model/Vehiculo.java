package com.mathiasuy.automotora.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@MappedSuperclass
public abstract class Vehiculo extends Auditable<String> {

	@Column(name = "modelo", nullable = false)
    private String modelo;
	@Column(name = "marca", nullable = false)
    private String marca;
	@Column(name = "descripcion", nullable = false)
    private String descripcion;

}

