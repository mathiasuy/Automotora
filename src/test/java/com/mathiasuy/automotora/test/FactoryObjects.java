package com.mathiasuy.automotora.test;

import com.mathiasuy.automotora.model.Auto;
import com.mathiasuy.automotora.requests.AutoRequest;

public class FactoryObjects {
	
	public static Auto generateAuto1() {
    	Auto auto = new Auto();
    	auto.setDescripcion(generateAutoRequest1().getDescripcion());
    	auto.setMarca(generateAutoRequest1().getMarca());
    	auto.setModelo(generateAutoRequest1().getModelo());
    	auto.setPuertas(generateAutoRequest1().getPuertas());
    	return auto;
	}
	
	public static AutoRequest generateAutoRequest1() {
    	AutoRequest request = new AutoRequest();
    	request.setDescripcion("Opel description");
    	request.setMarca("Opel");
    	request.setModelo("Rekord");
    	request.setPuertas(4);
    	return request;
	}
    
}
