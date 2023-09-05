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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author cmontes
 */
public class Pago implements Pagable{
    
    @FXML
    private Pane root;
    
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
    public void confirmar(){
        
    }
    
    @FXML
    public void cancelar(){
        
    }
    
    public void modoPago(){
        if(rbtarjeta.isSelected()){
            //incrementar valor a pagar por 10% TO DO
            HBox h = new HBox();
            
            VBox vlab = new VBox();
            Label nom = new Label("Nombre: ");
            Label num = new Label("Numero: ");
            Label caducidad = new Label("Caducidad: ");
            Label cvv = new Label("CVV: ");
            vlab.getChildren().addAll(nom,num,caducidad,cvv);
            
            VBox vdatos = new VBox();
            
            Label l = new Label("Ingrese los datos de su tarjeta:");
            
        }else if (rbefectivo.isSelected()){
            
        }
    }

    @Override
    public void generarTransaccion() {
        //TO DO
    }
    
    public static void mostrarVentanaPago() throws IOException{
        FXMLLoader fxmLoader = new FXMLLoader(Pedido.class.getResource("pago.fxml"));
        Parent root = fxmLoader.load();
        BaseHelado.scene = new Scene(root,600,400);
        BaseHelado.stage.setScene(BaseHelado.scene);
        BaseHelado.stage.setTitle("ArmaTuHelado5");
        BaseHelado.stage.show();
        
    }
    
    
}
