package com.automotora.service.controllers;

import com.automotora.service.exceptions.ControllerException;
import com.automotora.service.responses.VehiculoResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IVehiculoController {

    void agregarAuto(String marca, String modelo, int puertas) throws ControllerException;
    void agregarMoto(String marca, String modelo) throws ControllerException;
    void borrarVehiculo(String marca, String modelo) throws ControllerException;
    VehiculoResponse getVehiculo(String marca ,String modelo) throws ControllerException;
    List<VehiculoResponse> listarVehiculos() throws ControllerException;

}
