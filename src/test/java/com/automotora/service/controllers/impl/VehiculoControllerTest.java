package com.automotora.service.controllers.impl;

import com.automotora.service.controllers.IVehiculoController;
import com.automotora.service.dataaccess.IVehiculoDAO;
import com.automotora.service.exceptions.ControllerException;
import com.automotora.service.exceptions.DAOException;
import com.automotora.service.model.Auto;
import com.automotora.service.model.Moto;
import com.automotora.service.model.Vehiculo;
import com.automotora.service.responses.VehiculoResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

//runner de mockito que detecta las anotaciones
@RunWith(MockitoJUnitRunner.class)
public class VehiculoControllerTest {

//    private List<Vehiculo>

    // generamos un mock con anotaciones
    @Mock
    private IVehiculoDAO mockVehiculoDAO;

    @Mock
    private IVehiculoController controller;

    @Before
    public void setUp() throws Exception {
        BDDMockito.when(mockVehiculoDAO.getVehiculo("Yamaha","Mot"))
                .thenReturn(Optional.ofNullable(new Moto("Yamaha","Mot")));
        BDDMockito.when(mockVehiculoDAO.getVehiculo("Geely","CK"))
                .thenReturn(Optional.ofNullable(new Auto("Geely","CK",4)));
    }

    // ALTERNATIVO a la anotación: generamos un mock mediante el metodo mock
    //private IVehiculoDAO mockGenericDao = mock(IVehiculoDAO.class);

    @Test
    public void getVehiculoInvalido() {
        try {
            VehiculoResponse vehiculo = controller.getVehiculo("Motorola","CK");
            fail("Se encontró un vehículo inválido. Test fallido.");
        } catch (ControllerException e) {
            //Si termino aquí el caso es correcto
        }
    }

    @Test
    public void getVehiculoValido() {
        try {
            VehiculoResponse vehiculo = controller.getVehiculo("Geely","CK");
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void agregarAuto() {
        try {
            BDDMockito.doNothing().when(mockVehiculoDAO).insert(BDDMockito.isA(Vehiculo.class));
            mockVehiculoDAO.insert(new Auto("Geely","CK",4));
            BDDMockito.verify(controller).agregarAuto("Geely","CK",4);
//            mockVehiculoDAO.list();
        } catch (ControllerException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void agregarMoto() {
    }

    @Test
    public void borrarVehiculo() {
    }

    @Test
    public void listarVehiculos() {
    }

    @Test
    public void getVehiculo1() {
    }

    @Test
    public void agregarAuto1() {
    }

    @Test
    public void agregarMoto1() {
    }

    @Test
    public void borrarVehiculo1() {
    }

    @Test
    public void listarVehiculos1() {
    }
}