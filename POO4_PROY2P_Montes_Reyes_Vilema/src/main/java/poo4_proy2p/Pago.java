/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
 *
 * @author cmontes
 */
public class Pago implements Pagable, Initializable {
    private String nombre;
    private double total;
    private String tipo;

    public Pago(String nombre, double total, String tipo) {
        this.nombre = nombre;
        this.total = total;
        this.tipo = tipo;
    }
    public Pago(){
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTipo() {
        return tipo;
    }

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
    @FXML
    private ToggleGroup modopago;

    @FXML
    public void confirmar() throws IOException {
        OrdenGenerada.mostrarVentanaFinal();
    }

    @FXML
    public void cancelar() {

    }

    public void modoPago() {
        double total = obtenerSuma();
        Toggle seleccion = modopago.getSelectedToggle();
        if (rbtarjeta == seleccion) {
            //incrementar valor a pagar por 10% TO DO
            total += total * 0.10;
            tfvalor.setText(String.valueOf(total));
            tfvalor.setEditable(false);

            try {
                mostrarVentanaTarjeta();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else if (rbefectivo.isSelected()) {
            generarTransaccion();
            tfvalor.setText("Hola");
        }
    }

    @Override
    public Pago generarTransaccion() {
        Toggle seleccion = modopago.getSelectedToggle();
        double total = obtenerSuma();
        double iva = 0.83;
        double adicionalT = 0.63;
        Pago p = null;
        if (seleccion.equals(rbtarjeta)) {
                //incrementar valor a pagar por 10% TO DO
                total += total * 0.10;

                tfvalor.setText(String.valueOf(total));
                tfvalor.setEditable(false);
                tfadt.setText(String.valueOf(adicionalT));
                tfiva.setText(String.valueOf(iva));
                tftot.setText(String.valueOf(total+iva+adicionalT));
                tfadt.setEditable(false);
                tfiva.setEditable(false);
                tftot.setEditable(false);
                p = new Pago(VentanaOpciones.usuario.usuario,total+(iva*total)+(adicionalT*total),"Tarjeta");
                

                try {
                    mostrarVentanaTarjeta();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        }   else if (seleccion.equals(rbefectivo)) {
                tfvalor.setText(String.valueOf(total));
                tfadt.setText("0.0");
                tfiva.setText(String.valueOf("0.0"));
                tftot.setText(String.valueOf(total+(iva*total)));
                hdatos.getChildren().add(new Label("Acercarse a Caja para pagar tu pedido."));
                
                
                

                tfvalor.setEditable(false);
                tfadt.setEditable(false);
                tfiva.setEditable(false);
                tftot.setEditable(false);
                p = new Pago(VentanaOpciones.usuario.usuario,total+(iva*total),"Efectivo");
            }
        return p;
    }
    
    public double obtenerSuma(){
        double total = 0;
        for(double valor:VentanaOpciones.valoresAPagar){
            total+=valor;
        }
        return total;
    }

    public static void mostrarVentanaPago() throws IOException {
        FXMLLoader fxmLoader = new FXMLLoader(Pedido.class.getResource("pago.fxml"));
        Pago.root = fxmLoader.load();
        BaseHelado.scene = new Scene(root, 600, 400);
        BaseHelado.stage.setScene(BaseHelado.scene);
        BaseHelado.stage.setTitle("ArmaTuHelado5");
        BaseHelado.stage.show();

    }

    @FXML
    public  void mostrarVentanaTarjeta() throws IOException {
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

        hb.getChildren().addAll( vlab, vdatos);
        hb.setSpacing(20);
        HBox.setMargin(hb, new Insets(10,0,0,5));
        
        hdetalle.getChildren().add(hb);
        hdetalle.setSpacing(20);
        BackgroundFill backgroundFill = new BackgroundFill(Color.PINK, null, null);
        Background background = new Background(backgroundFill);
        hdetalle.setBackground(background);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generarTransaccion();
        //modoPago();
    }

}
