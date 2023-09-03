/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author cmontes
 */
public class BaseHelado implements Initializable {
    String nombre; 
    double precio;
    
    public BaseHelado(){
        
    }
    
    public BaseHelado(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    
//    public static void mostrarVentanaPedido1() throws IOException{
//        Stage stage = new Stage();
//        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("pedido.fxml"));
//        Parent root = fxmloader.load();
//        scene = new Scene(root,600,400);
//        stage.setScene(scene);
//        stage.setTitle("ArmaTuHelado");
//        stage.show();
//        
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //PARA CAMBIAR DE ESCENA
//        Usuario u=null;
//        Sabores.usuario = u;
//        try {
//                    VentanaOpciones.mostrarVentanaOpciones(u);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
    }
    
    @FXML
    private Button btnContinuar;
    
    @FXML
    private Label lblValor;
    
    @FXML
    private Pane rootH;
    
    @FXML
    private HBox hImag;
    
    @FXML
    private VBox vContenido;
    
    @FXML
    private HBox hPrecio;
    
    @FXML
    public void continuar(){
        
    }
}
