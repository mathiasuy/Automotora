package com.automotora.service.controllers.impl;

import com.automotora.service.controllers.IVehiculoController;
import com.automotora.service.dataaccess.IVehiculoDAO;
import com.automotora.service.exceptions.ControllerException;
import com.automotora.service.model.Auto;
import com.automotora.service.model.Moto;
import com.automotora.service.model.Vehiculo;
import com.automotora.service.responses.VehiculoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VehiculoController implements IVehiculoController {

    @Autowired
    @Qualifier("vehiculo")
    private IVehiculoDAO vehiculoDAO;

    private boolean control(String marca, String modelo) throws ControllerException {
        if (modelo.length() > 20){
            throw new ControllerException("El largo del nombre del model excede el límite");
        }
        if (marca.length() > 15){
            throw new ControllerException("El largo del nombre de la marca excede el límite permitido");
        }
        if (vehiculoDAO.exists(marca,modelo)){
            throw new ControllerException("¡El vehículo ya existe!");
        }
        return true;
    }

    @Override
    public VehiculoResponse getVehiculo(String marca, String modelo) throws ControllerException {
        return vehiculoDAO.getVehiculo(marca,modelo)
                .orElseThrow(()-> new ControllerException("¡El vehículo no existe!")).getResponse();
    }

    @Override
    public void agregarAuto(String marca, String modelo, int puertas) throws ControllerException {
        if (!(puertas == 4 || puertas == 2)){
            throw  new ControllerException("La cantidad de puertas no es correcta, o es 2 o es 4");
        }
        control(marca,modelo);
        vehiculoDAO.insert(new Auto(marca,modelo,puertas));
    }

    @Override
    public void modificar(Vehiculo vehiculo) throws ControllerException {
        control(vehiculo.getMarca(),vehiculo.getModelo());
        if (!vehiculoDAO.exists(vehiculo.getMarca(),vehiculo.getModelo())){
            throw new ControllerException("¡El vehículo no existe!");
        }
        vehiculoDAO.update(vehiculo);
    }

    @Override
    public void agregarMoto(String marca, String modelo) throws ControllerException {
        control(marca, modelo);
        vehiculoDAO.insert(new Moto(marca, modelo));
    }

    @Override
    public void borrarVehiculo(String marca, String modelo) throws ControllerException {
        if (!vehiculoDAO.exists(marca,modelo)){
            throw new ControllerException("¡El vehículo no existe!");
        }
        vehiculoDAO.delete(marca,modelo);
    }

    @Override
    public List<VehiculoResponse> listarVehiculos() throws ControllerException {
        List<VehiculoResponse> vehiculos = new ArrayList<>();
        vehiculoDAO.list().forEach(vehiculo->{
            vehiculos.add(vehiculo.getResponse());
        });
        return  vehiculos;
    }

    @Override
    public List<VehiculoResponse> buscarVehiculo(String criterio) throws ControllerException {
        List<VehiculoResponse> vehiculos = new ArrayList<>();
        vehiculoDAO.find(criterio).forEach(vehiculo->{
            vehiculos.add(vehiculo.getResponse());
        });
        return  vehiculos;
    }
}
