/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Clase Sabores nos ayuda rellenar ComboBoxs, además sirve de controlador para
 * sabores.fxml y nos ayuda a leer el archivo de sabores y a recuperar aquellos
 * seleccionados.
 *
 * @author Cecilia Montes
 * @author Kimberly Reyes
 * @author Daniel Vilema
 */
public class Sabores implements Initializable {

    String nombre;
    double precio;

    /**
     * Constructor sin argumentos de la clase Sabores. Este constructor crea una
     * instancia de Sabores sin establecer valores iniciales.
     */
    public Sabores() {

    }

    /**
     * Constructor de la clase Sabores con argumentos.
     *
     * @param nombre El nombre del sabor.
     * @param precio El precio del sabor.
     */
    public Sabores(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Obtiene el nombre del sabor.
     *
     * @return El nombre del sabor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del sabor.
     *
     * @return El precio del sabor.
     */
    public double getPrecio() {
        return precio;
    }

    @FXML
    private Pane root;

    @FXML
    private ImageView fondo;

    @FXML
    private HBox hbox;

    @FXML
    private VBox v1;

    @FXML
    private VBox v2;

    @FXML
    private ComboBox<Sabores> cbsabor1;

    @FXML
    private ComboBox<Sabores> cbsabor2;

    @FXML
    private Label lblValor;

    @FXML
    private Label lblmensaje;

    @FXML
    private Button btnContinuar;

    /**
     * Continúa con el proceso de selección de sabores y avanza a la siguiente
     * etapa. Verifica si se han seleccionado al menos una o dos opciones de
     * sabor y agrega las opciones seleccionadas a la lista de componentes y
     * valores a pagar. Luego, carga la ventana de selección de toppings.
     */

    @FXML
    public void continuar() {

        if (cbsabor1.getSelectionModel().isEmpty() && cbsabor2.getSelectionModel().isEmpty()) {
            try {
                throw new IncompleteStageException("Debe seleccionar al menos una opcion para continuar");
            } catch (IncompleteStageException i) {
//                i.printStackTrace();
                System.out.println(i.getMessage());
            }
            lblmensaje.setText("Debe seleccionar al menos una opcion para continuar");

        } else {

            if (cbsabor1.getValue() != null && cbsabor2.getValue() != null) {
                VentanaOpciones.componentes.add(cbsabor1.getValue());
                VentanaOpciones.valoresAPagar.add(cbsabor1.getValue().getPrecio());
                VentanaOpciones.componentes.add(cbsabor2.getValue());
                VentanaOpciones.valoresAPagar.add(cbsabor2.getValue().getPrecio());

            } else if (cbsabor1.getValue() != null) {
                VentanaOpciones.componentes.add(cbsabor1.getValue());
                VentanaOpciones.valoresAPagar.add(cbsabor1.getValue().getPrecio());

            } else if (cbsabor2.getValue() != null) {
                VentanaOpciones.componentes.add(cbsabor2.getValue());
                VentanaOpciones.valoresAPagar.add(cbsabor2.getValue().getPrecio());

            }

            try {
                Topping.cargarVentanaTopping();
            } catch (IOException ex) {
//                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Carga los sabores disponibles desde un archivo de texto en los ComboBoxes
     * de selección de sabores. Lee los sabores y sus precios desde un archivo
     * de texto llamado "sabores.txt" y los agrega como opciones a los
     * ComboBoxes cbsabor1 y cbsabor2.
     */
    public void cargarcb() {
        try (BufferedReader br = new BufferedReader(new FileReader("sabores.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] items = linea.split(",");
                cbsabor1.getItems().add(new Sabores(items[0], Double.parseDouble(items[1])));
                cbsabor2.getItems().add(new Sabores(items[0], Double.parseDouble(items[1])));

            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Usuario usuario;

    /**
     * Abre la ventana de selección de sabores y carga la interfaz gráfica
     * correspondiente. Utiliza el archivo FXML "sabores.fxml" y muestra la
     * ventana en la que los usuarios pueden seleccionar los sabores de su
     * helado.
     *
     * @throws IOException Si ocurre un error al cargar la ventana de selección
     * de sabores.
     */
    public static void mostrarVentanaSabores() throws IOException {

        FXMLLoader fxmLoader = new FXMLLoader(Sabores.class.getResource("sabores.fxml"));
        Parent root = fxmLoader.load();
        BaseHelado.scene = new Scene(root, 600, 400);
        BaseHelado.stage.setScene(BaseHelado.scene);
        BaseHelado.stage.setTitle("ArmaTuHelado2");
        BaseHelado.stage.show();
    }

    /**
     * Retorna una representación en cadena del sabor, incluyendo su nombre y
     * precio.
     *
     * @return Una cadena que representa el sabor en el formato "nombre -
     * precio".
     */
    @Override
    public String toString() {
        return nombre + " - " + precio;
    }

    /**
     * Inicializa la ventana de selección de sabores cargando las opciones
     * disponibles en los ComboBoxes y muestra el valor a pagar en el label
     * correspondiente. Este método se llama automáticamente al cargar la
     * interfaz gráfica.
     *
     * @param url La ubicación relativa de la interfaz gráfica.
     * @param rb Los recursos utilizados para la localización.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarcb();

        VentanaOpciones.cargarValorAPagar(lblValor);
    }
}
