package com.automotora.service.dataaccess.impl;

import com.automotora.service.dataaccess.IVehiculoDAO;
import com.automotora.service.exceptions.DAOException;
import com.automotora.service.model.Auto;
import com.automotora.service.model.KeyVehiculo;
import com.automotora.service.model.Moto;
import com.automotora.service.model.Vehiculo;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;


@Repository("vehiculo")
public class VehiculoDAO implements IVehiculoDAO {

    private HashMap<KeyVehiculo,Vehiculo> vehiculos = new HashMap<>();

    private void initBD(){
        if (vehiculos.isEmpty()) {
            List<Vehiculo> lista_vehiculos = new ArrayList<>();
            lista_vehiculos.add(new Auto("Peugeot", "C9", 4));
            lista_vehiculos.add(new Auto("Hyundai", "HK", 2));
            lista_vehiculos.add(new Moto("Yamaha", "Mot"));
            lista_vehiculos.add(new Moto("Yumbo", "YB"));
            lista_vehiculos.add(new Auto("Chevrolet", "CH", 4));

            lista_vehiculos.forEach(vehiculo -> {
                vehiculos.put(new KeyVehiculo(vehiculo),vehiculo);
            });
        }
    }

    @Override
    public boolean exists(String marca, String modelo) throws DAOException {
        initBD();
        return vehiculos.containsKey(new KeyVehiculo(marca,modelo));
    }

    @Override
    public void insert(Vehiculo vehiculo) throws DAOException {
        initBD();
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

    /**
     * Retorna la lista de vehiculos ordenados por marca
     * @return
     * @throws DAOException
     */
    @Override
    public List<Vehiculo> list() throws DAOException {
        initBD();
        List<Vehiculo> listVehiculos = new ArrayList<>(vehiculos.values());
        Comparator<Vehiculo> comparator = Comparator.comparing(Vehiculo::getMarca);
        Collections.sort(listVehiculos,comparator);
        return listVehiculos;
    }

    @Override
    public List<Vehiculo> find(String criterio) throws DAOException {
        initBD();
        return new ArrayList<>(vehiculos.values().stream()
                .filter(x ->
                        x.getMarca().contains(criterio)
                                ||x.getModelo().contains(criterio)
                                || (x instanceof Auto?String.valueOf(((Auto) x).getPuertas()).contains(criterio):false)
                ).collect(Collectors.toList()));
    }
}
