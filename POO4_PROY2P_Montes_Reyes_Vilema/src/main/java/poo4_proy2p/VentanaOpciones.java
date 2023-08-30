/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author cmontes
 */
public class VentanaOpciones {
    
    public static void mostrarVentanaOpciones(String usuario){
        Stage stage = new Stage();
        HBox h1 = new HBox();
        VBox v1 = new VBox();
        Label lmensaje = new Label("Bienvenido/a "+usuario);
        Button bEncuentra = new Button("Encuentra nuestros locales");
        Button bPedidos = new Button("Haz tus pedidos");
        v1.getChildren().addAll(lmensaje,bEncuentra,bPedidos);
        h1.getChildren().addAll(v1);//agregar imagen
        Scene scene = new Scene(h1,420,420,Color.BLACK);
        stage.setScene(scene);
        stage.show();
        
    }
}
