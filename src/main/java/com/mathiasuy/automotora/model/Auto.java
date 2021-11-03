package com.mathiasuy.automotora.model;


import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Auto extends Vehiculo{

	@Column(name = "puertas", nullable = false)
    private int puertas;

}
