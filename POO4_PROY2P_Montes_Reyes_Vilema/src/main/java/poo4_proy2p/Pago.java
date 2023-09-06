/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.ToggleGroup;

/**
 * Clase pago Nos ayuda a generar las ventanas finales para saber cuánto debe
 * pagar el usuario con efectivo o tarjeta.
 *
 * @author Cecilia Montes
 * @author Kimberly Reyes
 * @author Daniel Vilema
 */
public class Pago implements Pagable, Initializable {

    private String nombre;
    private double total;
    private String tipo;

    /**
     * Construye un nuevo objeto Pago con la información proporcionada.
     *
     * @param nombre El nombre del beneficiario (String).
     * @param total El monto total del pago (double).
     * @param tipo El tipo de pago (String), por ejemplo, tarjeta de crédito o
     * efectivo.
     */
    public Pago(String nombre, double total, String tipo) {
        this.nombre = nombre;
        this.total = total;
        this.tipo = tipo;
    }

    /**
     * Construye un nuevo objeto Pago sin información inicial.
     */
    public Pago() {

    }

    /**
     * Obtiene el nombre del Usuario.
     *
     * @return El nombre del Usuario como una cadena de caracteres.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo setter para establecer el nombre del Usuario.
     *
     * @param nombre asignado
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el total del Usuario.
     *
     * @return El total del Usuario como un double.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Metodo setter para establecer el total del Usuario.
     *
     * @param total asignado
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Obtiene el tipo de Usuario.
     *
     * @return El tipo de Usuario como una cadena de caracteres.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de Usuario.
     *
     * @param tipo El nuevo tipo de Usuario como una cadena de caracteres.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    @FXML
    private static Pane root;

    @FXML
    private ImageView fondo;

    @FXML
    private HBox hrb;

    @FXML
    private RadioButton rbtarjeta;

    @FXML
    private RadioButton rbefectivo;

    @FXML
    private HBox hdetalle;

    @FXML
    private HBox hdatos;

    @FXML
    private VBox vlabels;

    @FXML
    private VBox vtf;

    @FXML
    private TextField tfvalor;

    @FXML
    private TextField tfadt;

    @FXML
    private TextField tfiva;

    @FXML
    private TextField tftot;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnCancelar;

    ToggleGroup modopago;

    /**
     * Inicializa la ventana de pago con las opciones de pago disponibles.
     *
     * Este método se ejecuta cuando se carga la ventana de pago y configura las
     * opciones de pago disponibles, como efectivo y tarjeta. Además, calcula el
     * total a pagar y muestra la información relacionada con la opción de pago
     * seleccionada.
     *
     * @param url La ubicación relativa del recurso, o null si no está
     * disponible.
     * @param rb El ResourceBundle para localizar objetos, o null si no está
     * disponible.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup modopago = new ToggleGroup();
        rbefectivo.setToggleGroup(modopago);
        rbtarjeta.setToggleGroup(modopago);
        generarTransaccion();
        modopago = new ToggleGroup();

        double total = obtenerSuma();
        double iva = 0.83;
        double adicionalT = 0.63;
        Pago p = null;

        rbefectivo.setToggleGroup(modopago);
        rbefectivo.setToggleGroup(modopago);
        rbtarjeta.setToggleGroup(modopago);

        modopago.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == rbtarjeta) {
                    double total = obtenerSuma();
                    total += total * 0.10;

                    tfvalor.setText(String.valueOf(total));
                    tfvalor.setDisable(true);
                    tfadt.setText(String.valueOf(adicionalT));
                    tfiva.setText(String.valueOf(iva));
                    tftot.setText(String.valueOf(total + iva + adicionalT));
                    tfadt.setDisable(true);
                    tfiva.setDisable(true);
                    tftot.setDisable(true);
                    final Pago p = new Pago(VentanaOpciones.usuario.usuario, total + (iva * total) + (adicionalT * total), "Tarjeta");

                    try {
                        mostrarVentanaTarjeta();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    tfvalor.setText(String.valueOf(total));
                    tfadt.setText("0.0");
                    tfiva.setText(String.valueOf("0.0"));
                    tftot.setText(String.valueOf(total + (iva * total)));
                    hdatos.getChildren().add(new Label("Acercarse a Caja para pagar tu pedido."));

                    tfvalor.setDisable(true);
                    tfadt.setDisable(true);
                    tfiva.setDisable(true);
                    tftot.setDisable(true);
                    final Pago p = new Pago(VentanaOpciones.usuario.usuario, total + (iva * total), "Efectivo");
                }
            }
        });

    }

    /**
     * Este método se utiliza para confirmar una acción y mostrar una ventana
     * final.
     *
     * @throws IOException Si ocurre un error al intentar mostrar la ventana
     * final.
     */
    @FXML
    public void confirmar() throws IOException {
        OrdenGenerada.mostrarVentanaFinal();

    }

    /**
     * Cierra la ventana actual y borra los elementos del pedido.
     *
     * Este método se utiliza para cancelar el pedido actual. Cierra la ventana
     * en la que se encuentra y borra cualquier información relacionada con el
     * pedido, incluyendo los elementos seleccionados y los valores acumulados.
     */
    @FXML
    public void cancelar() {
        BaseHelado.stage.close();

        VentanaOpciones.componentes.clear();
        VentanaOpciones.valoresAPagar.clear();
    }

    /**
     * Calcula y devuelve la suma de los valores en la lista de precios.
     *
     * Este método itera a través de la lista de valores almacenados en
     * VentanaOpciones.valoresAPagar y calcula la suma total de esos valores.
     *
     * @return El valor total acumulado de los elementos seleccionados.
     */
    public double obtenerSuma() {
        double total = 0;
        for (double valor : VentanaOpciones.valoresAPagar) {
            total += valor;
        }
        return total;
    }

    /**
     * Muestra la ventana de pago para realizar una transacción de pago.
     *
     * @throws IOException Si ocurre un error al cargar la interfaz gráfica de
     * la ventana de pago.
     */
    public static void mostrarVentanaPago() throws IOException {
        FXMLLoader fxmLoader = new FXMLLoader(Pedido.class.getResource("pago.fxml"));
        Pago.root = fxmLoader.load();
        BaseHelado.scene = new Scene(root, 600, 400);
        BaseHelado.stage.setScene(BaseHelado.scene);
        BaseHelado.stage.setTitle("ArmaTuHelado5");
        BaseHelado.stage.show();

    }

    /**
     * Muestra una ventana para ingresar los datos de una tarjeta de crédito.
     *
     * @throws IOException Si ocurre un error al cargar la interfaz gráfica de
     * la ventana de tarjeta.
     */
    @FXML
    public void mostrarVentanaTarjeta() throws IOException {
        FXMLLoader fxmLoader = new FXMLLoader(Pedido.class.getResource("pago.fxml"));
        VBox vb = new VBox();
        HBox hb = new HBox();

        VBox vlab = new VBox();
        Label nom = new Label("Nombre: ");
        Label num = new Label("Numero: ");
        Label caducidad = new Label("Caducidad: ");
        Label cvv = new Label("CVV: ");
        vlab.getChildren().addAll(nom, num, caducidad, cvv);
        vlab.setSpacing(24);

        VBox vdatos = new VBox();
        TextField tfnombre = new TextField();
        TextField tfnum = new TextField();
        TextField tfcaduc = new TextField();
        TextField tfcvv = new TextField();
        vdatos.getChildren().addAll(tfnombre, tfnum, tfcaduc, tfcvv);
        vdatos.setSpacing(15);

        Label l = new Label("Ingrese los datos de su tarjeta:");
        l.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        Color colorP = Color.web("#3e0b63");
        l.setTextFill(colorP);

        root.getChildren().add(l);
        l.setLayoutX(293);
        l.setLayoutY(151);

        hb.getChildren().addAll(vlab, vdatos);
        hb.setSpacing(20);
        HBox.setMargin(hb, new Insets(10, 0, 0, 5));

        hdetalle.getChildren().add(hb);
        hdetalle.setSpacing(20);
        BackgroundFill backgroundFill = new BackgroundFill(Color.PINK, null, null);
        Background background = new Background(backgroundFill);
        hdetalle.setBackground(background);
    }

    @Override
    public Pago generarTransaccion() {
        return null;
    }

}
