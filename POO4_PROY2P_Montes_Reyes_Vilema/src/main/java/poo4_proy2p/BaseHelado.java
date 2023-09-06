/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Clase Base Helado Nos ayuda a generar la ventana de las bases, crearlas, leer
 * el archivo colocar las imágenes y seleccionar y recuperar las selecciones de
 * los usuarios.
 *
 * @author Cecilia Montes
 * @author Kimberly Reyes
 * @author Daniel Vilema
 */
public class BaseHelado implements Initializable {

    String nombre;
    double precio;
    ArrayList<ToggleButton> tgbuttons;
    ArrayList<Double> precios;
    /**
     * La escena actual en la aplicación JavaFX. Esta variable se utiliza para
     * gestionar y cambiar la escena actual en la interfaz de usuario.
     */
    public static Stage stage;
    /**
     * La ventana principal de la aplicación JavaFX. Esta variable representa la
     * ventana principal de la aplicación y se utiliza para mostrar y gestionar
     * ventanas y diálogos.
     */
    public static Scene scene;

    /**
     * Constructor vacío de la clase BaseHelado.
     *
     * Este constructor no realiza ninguna inicialización especial.
     */
    public BaseHelado() {

    }

    /**
     * Constructor de la clase BaseHelado que inicializa el nombre y el precio
     * del helado.
     *
     * @param nombre El nombre del helado.
     * @param precio El precio del helado.
     */
    public BaseHelado(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Obtiene el nombre del helado.
     *
     * @return El nombre del helado como una cadena de caracteres.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del helado.
     *
     * @return El precio del helado como un número de punto flotante (double).
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Carga una lista de bases de helado desde un archivo de texto y la
     * devuelve como una lista de objetos BaseHelado.
     *
     * @return Una lista de objetos BaseHelado que representan las bases de
     * helado cargadas desde el archivo.
     */
    public static ArrayList<BaseHelado> cargarBases() {
        ArrayList<BaseHelado> bh = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader("bases.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String[] datosp = linea.split(",");
                bh.add(new BaseHelado(datosp[0], Double.parseDouble(datosp[1])));
            }

        } catch (FileNotFoundException ex1) {
            System.out.println(ex1.getMessage());

        } catch (IOException ex2) {
            System.out.println(ex2.getMessage());
        }
        return bh;
    }

    /**
     * Inicializa la interfaz gráfica de usuario (GUI) de la sección de bases de
     * helado. Carga las bases de helado desde un archivo y muestra la
     * información en la GUI.
     *
     * @param url La ubicación relativa de la raíz del objeto a inicializar.
     * @param rb El recurso de recursos que se pasa a través de la
     * inicialización, o nulo si no hay ninguno.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<BaseHelado> bases = BaseHelado.cargarBases();
        ImageView imgView = null;
        ToggleButton tb = null;
        tgbuttons = new ArrayList<>();
        ToggleGroup tg = new ToggleGroup();
        precios = new ArrayList<>();

        for (BaseHelado base : bases) {
            try (FileInputStream input = new FileInputStream("src/main/resources/poo4_proy2p/" + base.getNombre() + ".jpg")) {
                Image image = new Image(input, 50, 50, false, false);
                imgView = new ImageView(image);

            } catch (IOException e) {
                System.out.println("No se encuentra la imagen");
            }
//            Label l1 = new Label(base.getNombre());
            Label l2 = new Label(String.valueOf(base.getPrecio()));
            precios.add(base.getPrecio());
            l2.setStyle("-fx-text-fill: purple; -fx-font-weight: bold; -fx-font-size: 15px;");

            tb = crearToggleButton(base.getNombre(), imgView, tg);
            tgbuttons.add(tb);

            VBox VBase = new VBox();
            VBase.getChildren().addAll(tb, l2);
            VBase.setAlignment(Pos.CENTER);
            VBase.setSpacing(10);
            VBase.setPadding(new Insets(10, 15, 10, 15));
            seccionBases.getChildren().add(VBase);

        }

    }

    /**
     * Crea y configura un botón de alternancia (ToggleButton) con un texto y
     * una imagen.
     *
     * @param texto El texto que se mostrará en el botón.
     * @param imgView La vista de la imagen que se mostrará junto al texto en el
     * botón.
     * @param tg El grupo de alternancia al que se asociará el botón.
     * @return Un objeto ToggleButton configurado con el texto, la imagen y el
     * grupo de alternancia.
     */
    public ToggleButton crearToggleButton(String texto, ImageView imgView, ToggleGroup tg) {
        ToggleButton tb = new ToggleButton(texto);
        tb.setToggleGroup(tg);

        DropShadow brillo = new DropShadow();
        brillo.setColor(Color.BLUE);
        brillo.setWidth(40);
        brillo.setHeight(40);

        tb.setStyle("-fx-text-fill: purple; -fx-font-weight: bold; -fx-font-size: 15px; -fx-background-color: #E0FFFF; -fx-border-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0.5, 3, 3);");
        tb.setGraphic(imgView);
        tb.setMinWidth(130);
        tb.setMinHeight(100);

        tb.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                tb.setEffect(brillo);
            } else {
                tb.setEffect(null);
            }
        });

        return tb;
    }

    /**
     * Verifica qué elementos de una lista de botones de alternancia
     * (ToggleButtons) están seleccionados.
     *
     * @param tgbuttons Una lista de botones de alternancia para verificar su
     * estado de selección.
     * @return Una lista de valores booleanos que indica si cada botón de
     * alternancia está seleccionado (true) o no (false).
     */
    public ArrayList<Boolean> elementoSeleccionado(ArrayList<ToggleButton> tgbuttons) {
        ArrayList<Boolean> retorno = new ArrayList<>();

        for (ToggleButton t : tgbuttons) {
            if (t.isSelected()) {
                retorno.add(true);
            } else {
                retorno.add(false);
            }
        }
        return retorno;
    }

    @FXML
    private Button btnContinuar;

    @FXML
    private Label lblValor;

    @FXML
    private Pane rootH;

    @FXML
    private FlowPane seccionBases;

    @FXML
    private Label lblmensaje;

    /**
     * Maneja la acción de continuar con la selección de opciones y muestra la
     * ventana de sabores. Agrega las opciones seleccionadas a los componentes y
     * valores a pagar si se ha seleccionado al menos una opción. Muestra un
     * mensaje de error si no se ha seleccionado ninguna opción.
     */
    @FXML
    public void continuar() {
        ArrayList<Boolean> lbooleanos = elementoSeleccionado(tgbuttons);
        int longitud = Math.min(tgbuttons.size(), lbooleanos.size());
        boolean contieneTrue = false;

        for (int i = 0; i < longitud; i++) {
            ToggleButton elemento = tgbuttons.get(i);
            boolean bool = lbooleanos.get(i);
            double precio = precios.get(i);

            if (bool) {
                VentanaOpciones.componentes.add(new BaseHelado(elemento.getText(), precio));
                VentanaOpciones.valoresAPagar.add(precio);

            }

        }

        for (boolean bool : lbooleanos) {
            if (bool) {
                contieneTrue = true;
                break;
            }
        }

        if (!contieneTrue) {
            try {
                throw new IncompleteStageException("Debe seleccionar al menos una opcion para continuar");
            } catch (IncompleteStageException ex1) {
                System.out.println(ex1.getMessage());
            }
            lblmensaje.setText("Debe seleccionar al menos una opcion para continuar");

        } else {
            try {
                Sabores.mostrarVentanaSabores();
            } catch (IOException ex2) {
                System.out.println(ex2.getMessage());
                ex2.printStackTrace();
            }

        }

    }
}
