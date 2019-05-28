package com.automotora.service.controllers.impl;

import com.automotora.service.dataaccess.IVehiculoDAO;
import com.automotora.service.exceptions.ControllerException;
import com.automotora.service.exceptions.DAOException;
import com.automotora.service.model.Auto;
import com.automotora.service.model.KeyVehiculo;
import com.automotora.service.model.Moto;
import com.automotora.service.model.Vehiculo;
import com.automotora.service.responses.AutoResponse;
import com.automotora.service.responses.MotoResponse;
import com.automotora.service.responses.VehiculoResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

//runner de mockito que detecta las anotaciones
@RunWith(MockitoJUnitRunner.class)
public class VehiculoControllerTest {

    private HashMap<KeyVehiculo,Vehiculo> vehiculos = new HashMap<>();

    // generamos un mock con anotaciones
    @Mock
    private IVehiculoDAO mockVehiculoDAO;

    @InjectMocks
    private VehiculoController controller;

    @Captor
    private ArgumentCaptor<Vehiculo> captor;

    private HashMap<KeyVehiculo,Vehiculo> vehiculosPersistidos = new HashMap<>();

    int k =0;
    private int cantidadAInsertar = 2;

    @Before
    public void setUp() throws Exception {
        //Se puede utilizar any() para representar el _todo cuando se pasa por parámetro
        // al metodo a simular
    }

    /*************************************************************************/
    /********************* Métodos auxiliares ********************************/
    /*************************************************************************/

//    El problema con esta metodología es que asume que siempre se llaman a todos los metodos
//    del DAO implicados en una operación cuando, ante un posible fallo, eso podría no ocurrir

    /**
     * Actualiza los otros métodos de la capa de datos y el map local auxiliar ante la llamada
     * al método insert de la capa
     * @param marca
     * @param modelo
     * @param puertas
     */
    private void agregarAutoSimulado(String marca, String modelo, int puertas){
        agregarVehiculoSimulado(new Auto(marca, modelo, puertas));
    }

    /**
     * Actualiza los otros métodos de la capa de datos y el map local auxiliar ante la llamada
     * al método insert de la capa
     * @param marca
     * @param modelo
     */
    private void agregarMotoSimulada(String marca, String modelo){
        agregarVehiculoSimulado(new Moto(marca, modelo));
    }

    /**
     * Actualiza los otros métodos de la capa de datos y el map local auxiliar ante la llamada
     * al método insert de la capa
     * @param vehiculo
     */
    private void agregarVehiculoSimulado(Vehiculo vehiculo){
        //También actualizo los otros métodos para obtener y listar
        try {
            //Esta es un control local para facilitar:
            vehiculosPersistidos.put(new KeyVehiculo(vehiculo),vehiculo);
            //Mocks:
            BDDMockito.when(mockVehiculoDAO.list()).thenReturn(new ArrayList<>(vehiculosPersistidos.values()));
            BDDMockito.when(mockVehiculoDAO.getVehiculo(vehiculo.getMarca(),vehiculo.getModelo()))
                    .thenReturn(Optional.ofNullable(vehiculosPersistidos.get(vehiculo)));
            BDDMockito.when(mockVehiculoDAO.exists(vehiculo.getMarca(),vehiculo.getModelo()))
                    .thenReturn(true);
        } catch (DAOException e) {
            fail("Ocurrió una excepción indebida");
        }
    }

    private void modificarVehiculoSimulado(Vehiculo vehiculo){
        //También actualizo los otros métodos para obtener y listar
        try {
            //Esta es un control local para facilitar:
            Vehiculo encontrado = vehiculosPersistidos.put(new KeyVehiculo(vehiculo),vehiculo);
            encontrado.setDescripcion(vehiculo.getDescripcion());
            if (encontrado instanceof Auto){
                ((Auto) encontrado).setPuertas(((Auto)vehiculo).getPuertas());
            }
            //Mocks:
            BDDMockito.when(mockVehiculoDAO.list()).thenReturn(new ArrayList<>(vehiculosPersistidos.values()));
            BDDMockito.when(mockVehiculoDAO.getVehiculo(vehiculo.getMarca(),vehiculo.getModelo()))
                    .thenReturn(Optional.ofNullable(vehiculosPersistidos.get(vehiculo)));
            BDDMockito.when(mockVehiculoDAO.exists(vehiculo.getMarca(),vehiculo.getModelo()))
                    .thenReturn(true);
        } catch (DAOException e) {
            fail("Ocurrió una excepción indebida");
        }
    }

    /**
     * Actualiza los otros métodos de la capa de datos y el map local auxiliar ante la llamada
     * al método borrar de la capa
     * @param marca
     * @param modelo
     */
    private void quitarVehiculoSimulado(String marca, String modelo){
        //También actualizo los otros métodos para obtener y listar
        try {
            //Esta es un control local para facilitar:
            vehiculosPersistidos.remove(new KeyVehiculo(marca,modelo));
            //Mocks:
            BDDMockito.when(mockVehiculoDAO.list()).thenReturn(new ArrayList<>(vehiculosPersistidos.values()));
            //Uso lenient para evitar excepcion de mockito y restaurar retornos de metodos
            BDDMockito.lenient().when(mockVehiculoDAO.getVehiculo(marca,modelo))
                    .thenReturn(Optional.ofNullable(null));
            BDDMockito.lenient().when(mockVehiculoDAO.exists(marca,modelo))
                    .thenReturn(false);
        } catch (DAOException e) {
            fail("Ocurrió una excepción indebida");
        }
    }

    /*************************************************************************/
    /********************** Fin métodos auxiliares ***************************/
    /*************************************************************************/

    // ALTERNATIVO a la anotación: generamos un mock mediante el metodo mock
    //private IVehiculoDAO mockGenericDao = mock(IVehiculoDAO.class);

    /**
     * En este método se chequea si se devuelve algo cuando se solicita un objeto inexistente
     * en la capa de datos simulada. Para esto se espera que se lance una excepción
     * del tipo ControllerException (de la capa lógica) con el mensaje correspndiente
     */
    @Test
    public void getVehiculoInvalido() {
        try {
            controller.getVehiculo("MotoPrueba2","Modelo1");
            fail("Se obtuvo un vehículo que no existe!");
        } catch (ControllerException e) {
            if (!e.getMessage().equals("¡El vehículo no existe!")){
                fail("Excepción equivocada");
            }
            //Test OK.
        }
    }

    /**
     * En este método se llama al test agregarMoto() para luego comprobar que se
     * pueda obtener ese vehículo
     */
    @Test
    public void getVehiculoValido() {
        try {
            agregarMoto();
            VehiculoResponse vehiculo = controller.getVehiculo("MotoPrueba1","Modelo");
            assertEquals(vehiculo instanceof MotoResponse,captor.getValue() instanceof Moto);
            assertEquals(vehiculo.getMarca(),captor.getValue().getMarca());
            assertEquals(vehiculo.getModelo(),captor.getValue().getModelo());
        } catch (ControllerException e) {
            fail("Ocurrió una excepción indebida");
        }
    }


    /**
     * Este test lo que hace es verificar que si intenté insertar un elemento desde el controlador,
     * este se insertó efectivamente en la capa DAO simulada, con los parámetros adecuados
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
     * Se utiliza la variable con anotación @Captor para poder indicar qué tipo de
     * objeto se pasa por parámetro en una función de insertado y captarlo cuando en el flujo se vaya a
     * insertar algo usando la función del verify(captor). Luego se puede usar captor.getValue()
     * para obtener el objeto que se pasó por parámetro durante el flujo del test.
     *
     */
    @Test
    public void agregarMoto() {
        try {

            controller.agregarMoto("MotoPrueba1","Modelo");
            //El siguiente metodo actualiza el map local y los otros metodos del dao para mantener coherencia
            agregarMotoSimulada("MotoPrueba1","Modelo");
            BDDMockito.verify(mockVehiculoDAO).insert(captor.capture());
            assertEquals(captor.getValue().getMarca(),"MotoPrueba1");
        } catch (ControllerException e) {
            fail("Ocurrió una excepción indebida");
        }

    }

    /**
     * Otra forma que hace lo mismo que lo anterior
     *
     * Nota: si se indica el parámetro de times(0) entonces se chequeará
     * que no se ha llamado al método agregarAuto()
     */
    @Test
    public void agregarAuto() {
        try {
            controller.agregarAuto("AutoPrueba1","Modelo",2);
            //El siguiente metodo actualiza el map local y los otros metodos del dao para mantener coherencia
            agregarAutoSimulado("AutoPrueba1","Modelo",2);
            BDDMockito.verify(mockVehiculoDAO).insert(captor.capture());
            Mockito.verify(mockVehiculoDAO,Mockito.times(1)).insert(captor.capture());
            assertEquals(captor.getValue().getMarca(),"AutoPrueba1");
        } catch (ControllerException e) {
            fail("Ocurrió una excepción indebida");
        }

    }

    /**
     * Este método inserta varias motos distintas
     */
    @Test
    public void agregarVariosVehiculosAlternados() {
        try {
            for (int i=cantidadAInsertar; i > 0; i--){
                if (0 == i % 2){//Vamos a insertar, para cada i, una moto cuando i sea par, sino un auto con 4 puertas
                    controller.agregarMoto(String.format("MotoPrueba %d",i),String.format("Modelo %d",i));
                    agregarMotoSimulada(String.format("MotoPrueba %d",i),String.format("Modelo %d",i));
                }else{
                    controller.agregarAuto(String.format("AutoPrueba %d",i),String.format("Auto %d",i),4);
                    agregarAutoSimulado(String.format("AutoPrueba %d",i),String.format("Auto %d",i),4);
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
                //Ya los agrego a la lista simulada
                agregarVehiculoSimulado(insertados.get(cantidadAInsertar));
            }
        } catch (ControllerException e) {
            fail("Ocurrió una excepción indebida");
        }
    }


    @Test
    public void borrarVehiculo() {
        try {
            agregarVariosVehiculosAlternados();
            //Me fijo que la lista que retorna el controlador contiene el vehículo
            if (
                    !controller.listarVehiculos().get(1).getMarca().equals("AutoPrueba 1") &&
                    controller.listarVehiculos().get(1).getModelo().equals("Auto 1")
            ){
                fail("No se encontró el vehículo a borrar");
            }
            int tamanioPrevio = controller.listarVehiculos().size();
            
            controller.borrarVehiculo("AutoPrueba 1","Auto 1");
            quitarVehiculoSimulado("AutoPrueba 1","Auto 1");

            //Ahora compruebo el tamaño de la lista retorno del controlador tras borrar
            if (tamanioPrevio <= controller.listarVehiculos().size()){
                fail("No se borró correctamente el elemento");
            }
        } catch (ControllerException e) {
            fail("Ocurrió una excepción indebida");
        }
    }

    @Test
    public void listarVehiculos() {
        try {
            agregarVariosVehiculosAlternados();
            List<VehiculoResponse> vehiculos = controller.listarVehiculos();
            //Primero chequeo que las cantidades sean las mismas para ante falla, no recurrir al loop
            //que es más costoso
            assertEquals(vehiculos.size(),captor.getAllValues().size());
            //Ahora uno por uno
            for (int i=0; i<cantidadAInsertar;i++){
                VehiculoResponse vehiculoResponse = vehiculos.get(i);
                Vehiculo vehiculo = captor.getAllValues().get(i);
                if (vehiculoResponse instanceof MotoResponse) {
                    assertEquals(vehiculoResponse instanceof MotoResponse, vehiculo instanceof Moto);
                    assertEquals(vehiculoResponse.getMarca(), vehiculo.getMarca());
                    assertEquals(vehiculoResponse.getModelo(), vehiculo.getModelo());
                }else{
                    assertEquals(vehiculoResponse instanceof AutoResponse, vehiculo instanceof Auto);
                    assertEquals(vehiculoResponse.getMarca(), vehiculo.getMarca());
                    assertEquals(vehiculoResponse.getModelo(), vehiculo.getModelo());
                    assertEquals(((AutoResponse)vehiculoResponse).getPuertas(), ((Auto)vehiculo).getPuertas());
                }
            }
        } catch (ControllerException e) {
            fail("Ocurrió una excepción indebida");
        }
    }

    @Test
    public void modificar(){
        try {
            agregarVariosVehiculosAlternados();
            Vehiculo vehiculo = new Auto("AutoPrueba 1","Auto 1",4){{
                setDescripcion("Descripción modificada");
            }};
//            BDDMockito.when(mockVehiculoDAO.exists("AutoPrueba 1", "Auto 1")).thenReturn(true);
            controller.modificar(vehiculo);
            modificarVehiculoSimulado(vehiculo);
            Mockito.verify(mockVehiculoDAO).update(captor.capture());
            assertEquals(captor.getValue().getMarca(),vehiculo.getMarca());
            assertEquals(captor.getValue().getDescripcion(),vehiculo.getDescripcion());
            assertEquals(captor.getValue().getModelo(),vehiculo.getModelo());
            if (captor.getValue() instanceof Auto == vehiculo instanceof Auto){
                assertEquals(((Auto)captor.getValue()).getPuertas(),((Auto) vehiculo).getPuertas());
            }
        } catch (ControllerException e) {
            fail("Ocurrió una excepción indebida");
        }
    }


    @Test
    public void comprobarOrdenDeListado(){
        //Este test no corresponde para esta capa, ya que de esto se encargó la capa DAO
    }

    /**
     * Como el filtrado se hace directo en la capa DAO simulando una consulta con where like,
     * no es tarea del controlador el filtrado, por lo tanto la parte de filtrado de
     * el test de filtrado no aplica para esta capa. Solo se comprueba el pasaje al response
     */
    @Test
    public void filtrado(){
        try {
            agregarVariosVehiculosAlternados();
            BDDMockito.when(mockVehiculoDAO.find("ba 1"))
                    .thenReturn(Arrays.asList(
                            new Auto("Prueba 1", "Modelo", 2),
                            new Moto("Prueba 111", "Modelo"),
                            new Moto("Prueba 11", "Modelo1")
                    ));
            List<VehiculoResponse> vehiculos = controller.buscarVehiculo("ba 1");
            //Chequeo la cantidad devuelta
            assertEquals(vehiculos.size(),3);
            //Chequeo lo devuelto
            AutoResponse auto = (AutoResponse) vehiculos.get(0);
            assertEquals(auto.getMarca(),"Prueba 1");
            assertEquals(auto.getModelo(),"Modelo");
            MotoResponse moto = (MotoResponse) vehiculos.get(1);
            assertEquals(moto.getMarca(),"Prueba 111");
            assertEquals(moto.getModelo(),"Modelo");
            moto = (MotoResponse) vehiculos.get(2);
            assertEquals(moto.getMarca(),"Prueba 11");
            assertEquals(moto.getModelo(),"Modelo1");
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

}