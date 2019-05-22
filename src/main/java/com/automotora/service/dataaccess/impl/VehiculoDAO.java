package com.automotora.service.dataaccess.impl;

import com.automotora.service.dataaccess.IVehiculoDAO;
import com.automotora.service.exceptions.DAOException;
import com.automotora.service.exceptions.DuplicateEntryException;
import com.automotora.service.model.Auto;
import com.automotora.service.model.Moto;
import com.automotora.service.model.Vehiculo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Repository("vehiculo")
public class VehiculoDAO implements IVehiculoDAO {

    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();

    private void initBD(){
        if (vehiculos.isEmpty()) {
            vehiculos.add(new Auto("Peugeot", "C9", 4));
            vehiculos.add(new Auto("Hinudai", "HK", 2));
            vehiculos.add(new Moto("Yamaha", "Mot"));
            vehiculos.add(new Moto("Yumbo", "YB"));
            vehiculos.add(new Auto("Chevrolet", "CH", 4));
        }
    }

    private String dir = "asd";
    private String url = "asdd";

    @Override
    public boolean exists(String marca, String modelo) throws DAOException {
        initBD();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMarca().equals(marca) && vehiculo.getModelo().equals(modelo)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void insert(Vehiculo vehiculo) throws DAOException {
        initBD();
        if (exists(vehiculo.getMarca(),vehiculo.getModelo())){
            throw new DuplicateEntryException("Ya existe el vehiculo");
        }
        vehiculos.add(vehiculo);
    }

    @Override
    public void delete(String marca, String modelo) throws DAOException {
        initBD();
        if (!exists(marca,modelo)){
            throw new DAOException("No existe el vehiculo");
        }
        for (int i = 0; i < vehiculos.size(); i++){
            Vehiculo vehiculo = vehiculos.get(i);
            if (vehiculo.getModelo().equals(modelo) && vehiculo.getMarca().equals(marca)){
                vehiculos.remove(i);
                break;
            }
        }
    }

    @Override
    public void update(Vehiculo vehiculo) throws DAOException {
        initBD();
        if (!exists(vehiculo.getMarca(),vehiculo.getModelo())){
            throw new DAOException("No existe el vehiculo");
        }
        for (int i = 0; i < vehiculos.size(); i++){
            Vehiculo ve = vehiculos.get(i);
            if (ve.getModelo().equals(vehiculo.getModelo()) && ve.getMarca().equals(vehiculo.getMarca())){
                Vehiculo mve = vehiculos.get(i);
                //.....
                break;
            }
        }
    }

    @Override
    public Optional<Vehiculo> getVehiculo(String marca, String modelo) throws DAOException {
        initBD();
        Vehiculo retorno = null;
        if (!exists(marca,modelo)){
            throw new DAOException("No existe el vehiculo");
        }
        for (int i = 0; i < vehiculos.size(); i++){
            Vehiculo ve = vehiculos.get(i);
            if (ve.getModelo().equals(modelo) && ve.getMarca().equals(marca)){
                retorno = vehiculos.get(i);
                break;
            }
        }
        return Optional.ofNullable(retorno);
    }

    @Override
    public List<Vehiculo> list() throws DAOException {
        initBD();
        return  vehiculos;
    }

    @Override
    public List<Vehiculo> find(String criterio) throws DAOException {
        initBD();
        return vehiculos;
    }
}
