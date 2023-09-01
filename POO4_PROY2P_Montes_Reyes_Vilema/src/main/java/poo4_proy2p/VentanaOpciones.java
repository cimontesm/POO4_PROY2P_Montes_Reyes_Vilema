/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cmontes
 */
public class VentanaOpciones implements Initializable {
    public Usuario usuario = null;
    
    public static void mostrarVentanaOpciones(Usuario usuario) throws IOException{
        FXMLLoader fxmLoader = new FXMLLoader(VentanaOpciones.class.getResource("VentanaOpciones.fxml"));
        Parent root = fxmLoader.load();
        App.scene = new Scene(root,600,400);
        App.stage.setScene(App.scene);
        App.stage.show();
    }
    
    @FXML
    private Label lblBienvenida;
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblBienvenida.setText("Bienvenido/a "+usuario);
            Thread pedidos = new Thread(()->{
                while(true){
                    try{
                        Platform.runLater(()->{
                            ventanaPedidos();
                        });
                        Thread.sleep(5*60*1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                }
                
            });
            pedidos.setDaemon(true);
            pedidos.start();
        
    }
    
    public void ventanaPedidos(){
        Stage stage = new Stage();
        VBox root = new VBox();
        Scene scene = new Scene(root,420,420);
        HBox h1 = new HBox();
        h1.getChildren().addAll((new TextField ("Hola")),new TextField ("Chao")); //agregar Pedido del Usuario
        root.getChildren().add(h1);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
