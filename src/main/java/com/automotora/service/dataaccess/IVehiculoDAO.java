package com.automotora.service.dataaccess;

import com.automotora.service.exceptions.DAOException;
import com.automotora.service.model.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface IVehiculoDAO {

    boolean exists(String marca, String modelo) throws DAOException;

    void insert(Vehiculo vehiculo) throws DAOException;

    void delete(String marca, String modelo) throws DAOException;

    void update(Vehiculo vehiculo) throws DAOException;

    Optional<Vehiculo> getVehiculo(String marca, String modelo) throws DAOException;

    List<Vehiculo> list() throws DAOException;

    List<Vehiculo> find(String criterio) throws DAOException;

}
