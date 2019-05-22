package com.automotora.service.controllers.impl;

import com.automotora.service.controllers.IVehiculoController;
import com.automotora.service.dataaccess.IVehiculoDAO;
import com.automotora.service.exceptions.ControllerException;
import com.automotora.service.exceptions.DAOException;
import com.automotora.service.model.Auto;
import com.automotora.service.model.KeyVehiculo;
import com.automotora.service.model.Moto;
import com.automotora.service.model.Vehiculo;
import com.automotora.service.responses.VehiculoResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

//runner de mockito que detecta las anotaciones
@RunWith(MockitoJUnitRunner.class)
public class VehiculoControllerTest {

    private HashMap<KeyVehiculo,Vehiculo> vehiculos = new HashMap<>();

    // generamos un mock con anotaciones
    @Mock
    private IVehiculoDAO mockVehiculoDAO;

    @Mock
    private IVehiculoController controller;

    int k =0;

    @Before
    public void setUp() throws Exception {
        BDDMockito.when(mockVehiculoDAO.getVehiculo("Yamaha","Mot"))
                .thenReturn(Optional.ofNullable(new Moto("Yamaha","Mot")));
        BDDMockito.when(mockVehiculoDAO.getVehiculo("Geely","CK"))
                .thenReturn(Optional.ofNullable(new Auto("Geely","CK",4)));

        Mockito.doAnswer((Answer)invoce->{
            k=12;
            return null;
        }).when(mockVehiculoDAO).insert(new Auto("","",4));
//        BDDMockito.doNothing(mockVehiculoDAO).insert(new Auto("Geely","CK",4)))
//                .thenCallRealMethod();
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
            if (k==12){
                fail("asdasdads");
            }
            controller.agregarAuto("","",4);
            if (k==12){
                fail("asdasdads");
            }
            VehiculoResponse vehiculo = controller.getVehiculo("Geely","CK");
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void agregarAuto() {
        try {
            BDDMockito.verify(controller).agregarAuto("Geely","CK",4);
            mockVehiculoDAO.list();
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