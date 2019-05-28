package com.automotora.rest.controllers;

import com.automotora.service.controllers.IVehiculoController;
import com.automotora.service.exceptions.ControllerException;
import com.automotora.service.filter.Filter;
import com.automotora.service.model.Vehiculo;
import com.automotora.service.requests.AutoRequest;
import com.automotora.service.requests.MotoRequest;
import com.automotora.service.responses.VehiculoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/ejemplo")
public class RestVehiculoController {

    @Autowired
    IVehiculoController controller;

    private VehiculoResponse addInfo(VehiculoResponse response){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String context = (String) request.getAttribute(Filter.CONTEXT);
        response.setInfo(context);
        return response;
    }

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

    @GetMapping("modificar")
    void modificarAuto(@RequestBody Vehiculo vehiculo) throws ControllerException{
            controller.modificar(vehiculo);
    }

    @GetMapping("borrar/{marca}/{modelo}")
    void modificarMoto(@PathVariable("marca") String marca,
                        @PathVariable("modelo") String modelo) throws ControllerException{
        controller.borrarVehiculo(marca,modelo);
    }

    @GetMapping("vehiculos/{marca}/{modelo}")
    VehiculoResponse getVehiculo(@PathVariable("marca") String marca ,
                                 @PathVariable("modelo") String modelo) throws ControllerException{
        return addInfo(controller.getVehiculo(marca,modelo));
    }

    @GetMapping("vehiculos")
    List<VehiculoResponse> listarVehiculos() throws ControllerException{
        List<VehiculoResponse> vehiculoResponses = controller.listarVehiculos();
        vehiculoResponses.forEach((vehiculoResponse -> addInfo(vehiculoResponse)));
        return vehiculoResponses;
    }

    @GetMapping("vehiculo")
    List<VehiculoResponse> buscarVehiculo(@QueryParam("q")
                                          @NotNull String q) throws ControllerException{
        List<VehiculoResponse> vehiculoResponses = controller.buscarVehiculo(q);
        vehiculoResponses.forEach((vehiculoResponse -> addInfo(vehiculoResponse)));
        return vehiculoResponses;
    }

}
