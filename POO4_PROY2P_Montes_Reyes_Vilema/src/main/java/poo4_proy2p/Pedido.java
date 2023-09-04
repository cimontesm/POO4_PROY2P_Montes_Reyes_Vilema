
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
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author cmontes
 */
public class Pedido {
    
    String nombre;
    double total;
    String id;
    
    @FXML
    private Pane root;

    public Pedido(String nombre, double total, String id) {
        this.nombre = nombre;
        this.total = total;
        this.id = id;
    }
    
    @FXML
    private ImageView fondo;
    
    @FXML
    private ListView lv;
    
    @FXML
    private Label lblValor;
    
    @FXML
    private Button btnConfirmar;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Button btnEliminar;
    
    @FXML
    public void confirmar(){
        
    }
    
    @FXML
    public void cancelar(){
        
    }
    
    @FXML
    public void eliminar(){
        
    }
    
    @Override
    public String toString(){
        return nombre+", "+id;
    }
    
    public static void mostrarVentanaPedido() throws IOException{
        FXMLLoader fxmLoader = new FXMLLoader(VentanaOpciones.class.getResource("pedidogen.fxml"));
        Parent root = fxmLoader.load();
        App.scene = new Scene(root,600,400);
        App.stage.setScene(App.scene);
        App.stage.setTitle("ArmaTuHelado2");
        App.stage.show();
    }
}
