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
 *
 * @author cmontes
 */
public class Sabores implements Initializable {

    String nombre;
    double precio;
    
    public Sabores(){
        
    }
    
    public Sabores(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

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
            
            if (cbsabor1.getValue()!=null && cbsabor2.getValue()!=null){
                VentanaOpciones.componentes.add(cbsabor1.getValue());
                VentanaOpciones.valoresAPagar.add(cbsabor1.getValue().getPrecio());
                VentanaOpciones.componentes.add(cbsabor2.getValue());
                VentanaOpciones.valoresAPagar.add(cbsabor2.getValue().getPrecio());
                
            } else if (cbsabor1.getValue()!=null){
                VentanaOpciones.componentes.add(cbsabor1.getValue());
                VentanaOpciones.valoresAPagar.add(cbsabor1.getValue().getPrecio());
                
            } else if (cbsabor2.getValue()!=null){
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

    public void cargarcb() {
        try ( BufferedReader br = new BufferedReader(new FileReader("sabores.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] items = linea.split(",");
                cbsabor1.getItems().add(new Sabores(items[0],Double.parseDouble(items[1])));
                cbsabor2.getItems().add(new Sabores(items[0],Double.parseDouble(items[1])));

            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Usuario usuario;

    public static void mostrarVentanaSabores() throws IOException {

        FXMLLoader fxmLoader = new FXMLLoader(Sabores.class.getResource("sabores.fxml"));
        Parent root = fxmLoader.load();
        BaseHelado.scene = new Scene(root, 600, 400);
        BaseHelado.stage.setScene(BaseHelado.scene);
        BaseHelado.stage.setTitle("ArmaTuHelado2");
        BaseHelado.stage.show();
    }
    
    @Override
    public String toString(){
        return nombre+" - "+precio;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarcb();
        
        double suma = 0.0;
        
        for (Double valor : VentanaOpciones.valoresAPagar){
            suma += valor;
        }
        lblValor.setText("Valor a pagar: "+suma);
        
    }
}
