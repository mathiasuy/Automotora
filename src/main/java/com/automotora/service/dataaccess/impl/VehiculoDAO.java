package com.automotora.service.dataaccess.impl;

import com.automotora.service.dataaccess.IVehiculoDAO;
import com.automotora.service.exceptions.DAOException;
import com.automotora.service.exceptions.DuplicateEntryException;
import com.automotora.service.model.Auto;
import com.automotora.service.model.KeyVehiculo;
import com.automotora.service.model.Moto;
import com.automotora.service.model.Vehiculo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;


@Repository("vehiculo")
public class VehiculoDAO implements IVehiculoDAO {

    private HashMap<KeyVehiculo,Vehiculo> vehiculos = new HashMap<>();

    private void initBD(){
        if (vehiculos.isEmpty()) {
            List<Vehiculo> lista_vehiculos = new ArrayList<>();
            lista_vehiculos.add(new Auto("Peugeot", "C9", 4));
            lista_vehiculos.add(new Auto("Hinudai", "HK", 2));
            lista_vehiculos.add(new Moto("Yamaha", "Mot"));
            lista_vehiculos.add(new Moto("Yumbo", "YB"));
            lista_vehiculos.add(new Auto("Chevrolet", "CH", 4));

            lista_vehiculos.forEach(vehiculo -> {
                vehiculos.put(new KeyVehiculo(vehiculo),vehiculo);
            });
        }
    }

    private String dir = "asd";
    private String url = "asdd";

    @Override
    public boolean exists(String marca, String modelo) throws DAOException {
        initBD();
        return vehiculos.containsKey(new KeyVehiculo(marca,modelo));
    }

    @Override
    public void insert(Vehiculo vehiculo) throws DAOException {
        initBD();
        if (exists(vehiculo.getMarca(),vehiculo.getModelo())){
            throw new DuplicateEntryException("Ya existe el vehiculo");
        }
        vehiculos.put(new KeyVehiculo(vehiculo),vehiculo);
    }

    @Override
    public void delete(String marca, String modelo) throws DAOException {
        initBD();
        if (!exists(marca,modelo)){
            throw new DAOException("No existe el vehiculo");
        }
        vehiculos.remove(new KeyVehiculo(marca,modelo));
    }

    @Override
    public void update(Vehiculo vehiculo) throws DAOException {
        initBD();
        if (!exists(vehiculo.getMarca(),vehiculo.getModelo())){
            throw new DAOException("No existe el vehiculo");
        }
        //.....
    }

    @Override
    public Optional<Vehiculo> getVehiculo(String marca, String modelo) throws DAOException {
        initBD();
        if (!exists(marca,modelo)){
            throw new DAOException("No existe el vehiculo");
        }
        return Optional.ofNullable(vehiculos.get(new KeyVehiculo(marca,modelo)));
    }

    @Override
    public List<Vehiculo> list() throws DAOException {
        initBD();
        return new ArrayList<>(vehiculos.values());
    }

    @Override
    public List<Vehiculo> find(String criterio) throws DAOException {
        initBD();
        return new ArrayList<>(vehiculos.values());
    }
}
