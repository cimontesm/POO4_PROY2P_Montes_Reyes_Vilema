
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Clase Pedido nos ayuda a leer el archivo, rellenar la escena y sirve de
 * controlador para 3 escenas pedidogen, eliminarsabor, cancelar compra
 *
 * @author Cecilia Montes
 * @author Kimberly Reyes
 * @author Daniel Vilema
 */
public class Pedido implements Initializable, Serializable {

    /**
     * Escena utilizada para mostrar la ventana de pago.
     */
    public static Scene scene;
    /**
     * Escenario (ventana) utilizado para mostrar la ventana de pago.
     */
    public static Stage stage;
    /**
     * Almacena el nombre del cliente que realizó el pedido.
     */
    String nombreC;
    /**
     * Almacena el costo total del pedido.
     */
    double total;
    static int id = (new Random()).nextInt(9000) + 1000;
    static Pedido p = new Pedido();

    /**
     * Constructor de la clase Pedido. Crea una nueva instancia de Pedido y crea
     * un nuevo objeto Stage asociado a la ventana del pedido.
     */
    public Pedido() {
        stage = new Stage();
    }

    /**
     * Constructor de la clase Pedido que recibe el nombre del cliente y el
     * total del pedido.
     *
     * @param nombreC El nombre del cliente que realiza el pedido.
     * @param total El total del pedido realizado por el cliente.
     */
    public Pedido(String nombreC, double total) {
        this.nombreC = nombreC;
        this.total = total;
        id++;
    }

    /**
     * Obtiene el nombre del cliente asociado a este pedido.
     *
     * @return El nombre del cliente.
     */
    public String getNombreC() {
        return nombreC;
    }

    /**
     * Obtiene el total del pedido.
     *
     * @return El total del pedido.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Obtiene el identificador único del pedido.
     *
     * @return El identificador único del pedido.
     */
    public static int getId() {
        return id;
    }
    /**
     * Representa la interfaz gráfica de la ventana de opciones para
     * personalizar un pedido.
     */

    @FXML
    private Pane root;
    /**
     * Imagen de fondo de la ventana de opciones.
     */
    @FXML
    private ImageView fondo;
/**
 * ListView que muestra la lista de elementos seleccionados para el pedido.
 */
    @FXML
    private ListView<String> lv;
/**
 * ListView que muestra la lista de elementos seleccionados para el pedido.
 */
    @FXML
    private Label lblValor;
    /**
     * Botón para cancelar y descartar el pedido actual.
     */
    @FXML
    private Button btnConfirmar;
/**
 * Botón para cancelar y descartar el pedido actual.
 */
    @FXML
    private Button btnCancelar;
    /**
     * Botón para eliminar un elemento seleccionado del pedido.
     */
    @FXML
    private Button btnEliminar;

    /**
     * Obtiene la vista de lista (ListView) utilizada para mostrar los elementos
     * en la interfaz gráfica.
     *
     * @return La vista de lista (ListView) que muestra los elementos.
     */
    public ListView<String> getLv() {
        return lv;
    }

    /**
     * Establece la ListView utilizada para mostrar los elementos en el pedido.
     *
     * @param lv La ListView que se utilizará para mostrar los elementos del
     * pedido.
     */
    public void setLv(ListView<String> lv) {
        this.lv = lv;
    }

    /**
     * Confirma el pedido actual, calcula el total, guarda la información del
     * pedido en un archivo de texto y en un archivo binario, y muestra la
     * ventana de pago.
     */
    @FXML
    public void confirmar() {
        double total = 0.0;

        for (Double valor : VentanaOpciones.valoresAPagar) {
            total += valor;
        }

        p = new Pedido(VentanaOpciones.usuario.getUsuario(), total);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("pedidos.txt", true))) {
            bw.write(p.toString() + "\n");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(String.valueOf(Pedido.getId()) + "Pedido.bin"))) {
            out.writeObject(p);

        } catch (IOException ex2) {
            System.out.println(ex2.getMessage());
        }

        try {
            Pago.mostrarVentanaPago();
        } catch (IOException e) {
//            System.out.println(e);
            e.printStackTrace();
        }
    }

//    public static ListView<String> lv;
    /**
     * Inicializa la ventana de pedidos, carga los elementos en una ListView y
     * carga el valor a pagar.
     *
     * @param url La ubicación del recurso no utilizado.
     * @param rb El conjunto de recursos no utilizado.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarListView();

        VentanaOpciones.cargarValorAPagar(lblValor);

    }

    /**
     * Carga elementos en una ListView con base en los componentes
     * seleccionados. Los elementos pueden ser bases, sabores o toppings y se
     * muestran en el ListView.
     */
    public void cargarListView() {
        ArrayList<String> elementos = new ArrayList<>();
//        ListView<String> lv2 = lv;

        for (Object componente : VentanaOpciones.componentes) {
            if (componente instanceof BaseHelado) {
                BaseHelado bh = (BaseHelado) componente;
                elementos.add("Base: " + bh.getNombre());

            } else if (componente instanceof Sabores) {
                Sabores s = (Sabores) componente;
                elementos.add("Sabor: " + s.getNombre());

            } else if (componente instanceof Topping) {
                Topping tp = (Topping) componente;
                elementos.add("Topping: " + tp.getNombre());

            }

        }

        ObservableList<String> items = FXCollections.observableArrayList(elementos);
        lv.setItems(items);
        lv.setDisable(false);

    }

    /**
     * Abre una ventana de confirmación para cancelar la compra actual. Esta
     * ventana permite al usuario confirmar o cancelar la cancelación del
     * pedido.
     *
     * @throws IOException Si hay un error al cargar la ventana de confirmación.
     */
    @FXML
    public void cancelar() throws IOException {
        FXMLLoader fxmLoader = new FXMLLoader(Pedido.class.getResource("cancelarcompra.fxml"));
        Parent root = fxmLoader.load();
        Pedido.scene = new Scene(root, 400, 200);
        Pedido.stage.setScene(Pedido.scene);
        Pedido.stage.setTitle("Mensaje");
        Pedido.stage.show();

        CancelarPedido cancelarPedidoController = fxmLoader.getController();

        cancelarPedidoController.setStageCancelar(Pedido.stage);
    }

    /**
     * Abre una ventana de confirmación para eliminar un sabor del pedido
     * actual. Esta ventana permite al usuario confirmar o cancelar la
     * eliminación del sabor seleccionado.
     *
     * @throws IOException Si hay un error al cargar la ventana de confirmación.
     */
    @FXML
    public void eliminar() throws IOException {
        FXMLLoader fxmLoader = new FXMLLoader(Pedido.class.getResource("eliminarsabor.fxml"));
        Parent root = fxmLoader.load();
        Pedido.scene = new Scene(root, 400, 200);
        Pedido.stage.setScene(Pedido.scene);
        Pedido.stage.setTitle("Mensaje");
        Pedido.stage.show();

        EliminarSabor eliminarSaborController = fxmLoader.getController();

        eliminarSaborController.setStageEliminar(Pedido.stage);
    }

    /**
     * Devuelve una representación en cadena del objeto Pedido en el formato:
     * id,nombreC,total.
     *
     * @return Una cadena que representa el objeto Pedido.
     */
    @Override
    public String toString() {
        return id + "," + nombreC + "," + total;
    }

    /**
     * Muestra la ventana de pedido de toppings.
     *
     * @throws IOException Si hay un error al cargar la ventana de pedido.
     */
    public static void mostrarVentanaPedido() throws IOException {
        FXMLLoader fxmLoader = new FXMLLoader(Topping.class.getResource("pedidogen.fxml"));
        Parent root = fxmLoader.load();
        BaseHelado.scene = new Scene(root, 600, 400);
        BaseHelado.stage.setScene(BaseHelado.scene);
        BaseHelado.stage.setTitle("ArmaTuHelado4");
        BaseHelado.stage.show();
    }

    /**
     * Verifica si un elemento es un sabor basado en su etiqueta.
     *
     * @param elemento La etiqueta del elemento a verificar.
     * @return true si el elemento es un sabor, false en caso contrario.
     */
    public static boolean esSabor(String elemento) {
        return elemento.startsWith("Sabor: ");
    }

}
