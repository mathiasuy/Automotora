package com.automotora.rest.controllers;

import com.automotora.service.controllers.IVehiculoController;
import com.automotora.service.exceptions.ControllerException;
import com.automotora.service.requests.AutoRequest;
import com.automotora.service.requests.MotoRequest;
import com.automotora.service.responses.VehiculoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;
import java.util.List;

@RestController
@RequestMapping("/ejemplo")
public class RestVehiculoController {

    @Autowired
    IVehiculoController controller;

    @PostMapping("agregar/auto")
    void agregarAuto(@RequestBody AutoRequest auto) throws ControllerException{
        controller.agregarAuto(auto.getMarca(),auto.getModelo(),auto.getPuertas());
    }

    @PostMapping("agregar/moto")
    void agregarMoto(@RequestBody MotoRequest moto) throws ControllerException{
        controller.agregarMoto(moto.getMarca(),moto.getModelo());
    }

    @GetMapping("borrar/{marca}/{modelo}")
    void borrarVehiculo(@PathVariable("marca") String marca,
                        @PathVariable("modelo") String modelo) throws ControllerException{
        controller.borrarVehiculo(marca,modelo);
    }

    @GetMapping("vehiculos/{marca}/{modelo}")
    VehiculoResponse getVehiculo(@PathVariable("marca") String marca ,
                                 @PathVariable("modelo") String modelo) throws ControllerException{
        return controller.getVehiculo(marca,modelo);
    }

    @GetMapping("vehiculos")
    List<VehiculoResponse> listarVehiculos() throws ControllerException{
        return controller.listarVehiculos();
    }

}
