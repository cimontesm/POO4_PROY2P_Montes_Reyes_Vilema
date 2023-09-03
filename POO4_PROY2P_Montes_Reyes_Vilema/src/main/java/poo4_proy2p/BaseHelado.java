/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cmontes
 */
public class BaseHelado {
    String nombre; 
    double precio;
    public static Scene scene;
    
    public BaseHelado(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    
    public static void mostrarVentanaPedido1() throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("pedido.fxml"));
        Parent root = fxmloader.load();
        scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("ArmaTuHelado");
        stage.show();
        
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
