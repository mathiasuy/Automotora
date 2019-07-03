package com.automotora.service.controllers.impl;

import com.automotora.rest.config.AutomotoraConfiguration;
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
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author Mathias Battistella - Magenta Innova
 *
 * Esta forma de chequeo no necesita que la capa DAO esté en funcionamiento
 * NI QUE ESTÉ IMPLEMENTADA SU INTERFAZ.
 *
 * Tampoco se precisa preparación alguna de los metodos para insertar.
 *
 * El método verify() no presta atención a los datos que ya pueda haber en la capa DAO si esta contiene
 * algo previo al test.
 *
 * Es necesario haber invocado al menos una vez al método queverifica verify()
 * (el metodo agregar del controlador)
 *
 * Para que funcione correctamente el controlador se debe indicar con la anotación @InjectMock
 * y la capa a simular con @Mock
 *
 * Se utiliza la variable con anotación @Captor para que mockito la inicialice. Esta sirve para
 * poder indicar qué tipo de objeto se pasa por parámetro en una función de insertado y captarlo
 * cuando en el flujo se vaya a insertar algo usando la función del verify(captor). Luego se puede
 * usar captor.getValue() para obtener el objeto que se pasó por parámetro durante el flujo del test.
 *
 * Mockito no permite que se setteen métodos del dao que luego no se usan, en caso de querer forzar se puede
 * usar BDDMockito.lenient().when...
 *
 */


@SpringBootTest(classes = AutomotoraConfiguration.class)
@RunWith(SpringRunner.class)//runner de mockito que detecta las anotaciones
@TestPropertySource(locations = "classpath:test.properties")
public class VehiculoServiceTest {


    // generamos un mock con anotaciones
    // ALTERNATIVO a la anotación: generamos un mock mediante el metodo mock
    //private IVehiculoDAO mockGenericDao = mock(IVehiculoDAO.class);
    @Mock
    private IVehiculoDAO mockVehiculoDAO;

    @InjectMocks
    private VehiculoController controller;

    @Captor
    private ArgumentCaptor<Vehiculo> captor;

    @Autowired
    private Environment environment;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(controller, "largoModelo", Integer.valueOf(environment.getProperty("varchar.marca.largo")));
        ReflectionTestUtils.setField(controller, "largoMarca", Integer.valueOf(environment.getProperty("varchar.modelo.largo")));
        ReflectionTestUtils.setField(controller, "msjLargoModeloFail", environment.getProperty("mensajes.varchar.modelo.largo.fail"));
        ReflectionTestUtils.setField(controller, "msjLargoMarcaFail", environment.getProperty("mensajes.varchar.marca.largo.fail"));

        //when(myProperty.get()).thenReturn("my property value");
//        MockitoAnnotations.initMocks(controller);// para forzar la inicialización
        //En este método se coloca _todo lo que se desee que se ejecute antes de cada método @Test
        //Otras opciónes: @BeforeEach, @BeforeClass (para métodos estáticos), etc...

        //Se puede utilizar any() para representar el _todo cuando se pasa por parámetro
        // al metodo a simular

        //En caso de haber variables parametrizadas en los métodos del controlador, se puede
        //usar: ReflectionTestUtils.setField(controlAccesoService, "largoUsuario", 15);

        //si mockito no inicializa, se puede usar:
        //MockitoAnnotations.initMocks(this); para forzar la inicialización

        //Se puede usar List mockWithLogger = BDDMockito.mock(List.class, BDDMockito.withSettings().verboseLogging());
        //Para el logger de mockito
    }

    /**
     * En este método se chequea si se devuelve algo cuando se solicita un objeto inexistente
     * en la capa de datos simulada. Para esto se espera que se lance una excepción
     * del tipo ControllerException (de la capa lógica) con el mensaje correspndiente
     * @throws ControllerException 
     */
    @Test(expected=ControllerException.class) //Quiero que se lance esa excepci�n como condici�n para que el test pase
    public void getVehiculoInvalido() throws ControllerException {
            Moto moto = new Moto("Moto","Modelo");
            //Indico que no existe, por si se usa el metodo exists en el controlador
            BDDMockito.when(mockVehiculoDAO.exists(moto.getMarca(),moto.getModelo())).thenReturn(false);
            BDDMockito.when(mockVehiculoDAO.getVehiculo(moto.getMarca(),moto.getModelo())).thenReturn(Optional.ofNullable(null));
            controller.getVehiculo(moto.getMarca(),moto.getModelo());
    }

    /**
     * En este método se llama al test agregarMoto() para luego comprobar que se
     * pueda obtener ese vehículo
     * La implementación de este test se hará de abajo hacia arriba
     * @throws ControllerException 
     */
    @Test
    public void getVehiculoValidoDAOAControlador() throws ControllerException {
        Moto moto = new Moto("Moto","Modelo");
        BDDMockito.when(mockVehiculoDAO.getVehiculo(moto.getMarca(),moto.getModelo())).thenReturn(Optional.ofNullable(moto));
        //Asumo que existe, para eso setteo el metodo del dao en true para este objeto
        BDDMockito.when(mockVehiculoDAO.exists(moto.getMarca(),moto.getModelo())).thenReturn(true);
        VehiculoResponse vehiculo = controller.getVehiculo(moto.getMarca(),moto.getModelo());
        assertEquals(vehiculo,moto.getResponse());
    }

    /**
     * La implementación de este test se hará de abajo hacia arriba
     * @throws ControllerException 
     */
    @Test
    public void getVehiculoValidoControladorADAO() throws ControllerException {
        String marcaString = "Moto";
        String modeloString = "Modelo";
        Moto moto = new Moto(marcaString,modeloString);
        //Asumo que existe para que el caso sea feliz, no se si se usará en la
        //implementación del controlador pero tampoco lo obligo
        BDDMockito.when(mockVehiculoDAO.exists(marcaString,modeloString)).thenReturn(true);
        //También ya setteo el retorno de getVehiculo en el DAO para ir de abajo hacia arriba
        BDDMockito.when(mockVehiculoDAO.getVehiculo(marcaString,modeloString))
                .thenReturn(Optional.ofNullable(moto));
        //Mando los datos al controlador
        VehiculoResponse response = controller.getVehiculo(marcaString,modeloString);
        //Comparo la respuesta con lo esperado
        assertEquals(response,moto.getResponse());
    }


    /**
     * Este test lo que hace es verificar que si intenté insertar un elemento desde el controlador,
     * este se insertó efectivamente en la capa DAO simulada, con los parámetros adecuados
     *
     * El caso de prueba simula cuando el vehículo que llega al controlador para tratar de insertarse
     * en la base de datos, aún no existe en ella.
     * @throws ControllerException 
     *
     */
    @Test
    public void agregarMoto() throws ControllerException {
        Moto moto = new Moto("Moto 1", "modelo");
        //Se prepara el panorama indicando que la moto no existe para la capa DAO
        BDDMockito.when(mockVehiculoDAO.exists(moto.getMarca(),moto.getModelo())).thenReturn(false);
        //Ahora se envía la moto al controlador
        controller.agregarMoto(moto.getMarca(),moto.getModelo());
        //Ahora capturo lo que le llegó al insert del DAO, ¿Llegará a la capa DAO?
        // tendría que haber llegado info ya que no existía previamente un elemento con esa llave
        BDDMockito.verify(mockVehiculoDAO).insert(captor.capture());
        //Lo compruebo
        assertEquals(captor.getValue(),moto);
    }

    /**
     * El siguiente método es análogo al anterior pero para Autos.
     * Nota: si se indica como segundo parámetro en verify() times(0) entonces se chequeará
     * que no se ha llamado al método agregarAuto(), si se indica un valor > 0 se
     * obligará a comprobar que se realizaron varios inert y deberá ser exactamente
     * igual a la cantidad de llamados a el insert. Si no se coloca, se asume 1 llamado solo (y solo 1)
     * @throws ControllerException 
     */
    @Test
    public void agregarAuto() throws ControllerException {
        Auto auto = new Auto("AutoPrueba1","Modelo",4);
        BDDMockito.when(mockVehiculoDAO.exists(auto.getMarca(),auto.getModelo())).thenReturn(false);
        controller.agregarAuto(auto.getMarca(),auto.getModelo(),auto.getPuertas());
        BDDMockito.verify(mockVehiculoDAO).insert(captor.capture());
        assertEquals(captor.getValue(),auto);
    }

    /**
     * Este método inserta varios vehículos distintos
     * @throws ControllerException 
     */
    @Test
    public void agregarVariosVehiculosAlternados() throws ControllerException {
        int cantidadAInsertar = 20;
        for (int i=cantidadAInsertar; i > 0; i--){
            if (0 == i % 2){
                //Vamos a insertar, para cada i, una moto cuando i sea par, sino un auto con 4 puertas
                Moto moto = new Moto(String.format("MotoPrueba %d",i),String.format("Modelo %d",i));
                BDDMockito.when(mockVehiculoDAO.exists(moto.getMarca(),moto.getModelo())).thenReturn(false);
                controller.agregarMoto(moto.getMarca(),moto.getModelo());
                BDDMockito.when(mockVehiculoDAO.exists(moto.getMarca(),moto.getModelo())).thenReturn(true);
            }else{
                Auto auto = new Auto(String.format("AutoPrueba %d",i),String.format("Auto %d",i),4);
                BDDMockito.when(mockVehiculoDAO.exists(auto.getMarca(),auto.getModelo())).thenReturn(false);
                controller.agregarAuto(auto.getMarca(),auto.getModelo(),auto.getPuertas());
                BDDMockito.when(mockVehiculoDAO.exists(auto.getMarca(),auto.getModelo())).thenReturn(true);
            }
        }
        //Ahora chequeo uno por uno que hayan llegado correctamente a la capa de datos.

        // En la línea de abajo debe indicarse EXACTAMENTE cuántas veces se ejecutó el insert del DAO
        Mockito.verify(mockVehiculoDAO,Mockito.times(cantidadAInsertar)).insert(captor.capture());

        //Obtengo la lista de insertados:
        List<Vehiculo> insertados = captor.getAllValues();

        //Chequeo tamaños de listas:
        assertEquals(insertados.size(),cantidadAInsertar);

        //Los chequeo uno por uno:
        for (int i=0; i >= cantidadAInsertar; i++){
            if (0 == i % 2) {//Decíamos que si i es par se había insertado una Moto, sino un auto
                assertEquals(insertados.get(i).getMarca(), String.format("MotoPrueba %d", i));
            }else{
                //Para el caso del auto también puedo chequear la cantidad de puertas...
                assertEquals(insertados.get(i).getMarca(), String.format("AutoPrueba %d", i));
                assertEquals(((Auto)insertados.get(i)).getPuertas(), 4);
            }
        }
    }


    /***
     * Esta prueba sería como un "TopDown", prueba desde el rest hacia la capa de datos
     * @throws ControllerException 
     */
    @Test
    public void borrarVehiculo() throws ControllerException {
        String marcaDeLaVictima = "Peugeot";
        String modeloDeLaVictima = "C12";
        ArgumentCaptor<String> marca = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> modelo = ArgumentCaptor.forClass(String.class);
        //El vehiculo debe existir para que el caso sea feliz
        BDDMockito.when(mockVehiculoDAO.exists(marcaDeLaVictima,modeloDeLaVictima)).thenReturn(true);
        //Prueba de arriba hacia abajo, envìo al controlador
        controller.borrarVehiculo(marcaDeLaVictima,modeloDeLaVictima);
        //Capturo lo que le llegò al DAO
        BDDMockito.verify(mockVehiculoDAO).delete(marca.capture(),modelo.capture());
        //la línea de abajo es en la que se comprueba lo que se pide en este test con lo que le llegò al dao
        assertEquals(marca.getValue(),marcaDeLaVictima);
        assertEquals(modelo.getValue(),modeloDeLaVictima);
    }

    @Test
    public void listarVehiculos() throws ControllerException {
        Vehiculo[] vehiculos = {
                new Auto("Prueba 1", "Modelo", 2),
                new Moto("Prue11", "Modba 1elo"),
                new Moto("Prueba 12", "Modelo1"),
                new Moto("Prueba 11", "Modelo1"){{
                    setDescripcion("ba 1");
                }}
        };
        VehiculoResponse[] salidaEsperada = new VehiculoResponse[vehiculos.length];
        for (int i = 0; i < vehiculos.length; i++) {
            salidaEsperada[i] = vehiculos[i].getResponse();
        }
        BDDMockito.when(mockVehiculoDAO.list()).thenReturn(Arrays.asList(vehiculos));
        List<VehiculoResponse> vehiculosResponse = controller.listarVehiculos();
        assertArrayEquals(vehiculosResponse.toArray(),salidaEsperada);
    }

    @Test
    public void modificar() throws ControllerException{
       Vehiculo vehiculo = new Auto("AutoPrueba 1","Auto 1",4){{
            setDescripcion("Descripción modificada");
        }};
       //Indico que el vehiculo existe, ya que este es un caso feliz :).
        BDDMockito.when(mockVehiculoDAO.exists(vehiculo.getMarca(), vehiculo.getModelo())).thenReturn(true);
        //Mando al contorlador
        controller.modificar(vehiculo);
        //Capturo lo que llega al dao
        Mockito.verify(mockVehiculoDAO).update(captor.capture());
        //Compruebo
        assertEquals(captor.getValue(),vehiculo);
    }


    @Test
    public void comprobarOrdenDeListado(){
        //Este test no corresponde para esta capa, ya que de esto se encargó la capa DAO
    }

    /**
     * Como el filtrado se hace directo en la capa DAO simulando una consulta con where like,
     * no es tarea del controlador el filtrado, por lo tanto la parte de filtrado de
     * el test de filtrado no aplica para esta capa. Solo se comprueba el pasaje al response
     * @throws ControllerException 
     *
     */
    @Test
    public void filtrado() throws ControllerException{
        Vehiculo[] vehiculos = {
            new Auto("Prueba 1", "Modelo", 2),
            new Moto("Prue11", "Modba 1elo"),
            new Moto("Prueba 12", "Modelo1"),
            new Moto("Prueba 11", "Modelo1"){{
                setDescripcion("ba 1");
            }}
        };
        BDDMockito.when(mockVehiculoDAO.find("ba 1")).thenReturn(Arrays.asList(vehiculos));
        VehiculoResponse[] salidaEsperada = new VehiculoResponse[vehiculos.length];
        for (int i = 0; i < vehiculos.length; i++) {
            salidaEsperada[i] = vehiculos[i].getResponse();
        }
        //Mando al controlador una busqueda (la capa dao es la encargada de la busqueda)
        List<VehiculoResponse> encontrados = controller.buscarVehiculo("ba 1");
        //Chequeo lo devuelto
        assertArrayEquals(encontrados.toArray(),salidaEsperada);
    }

}