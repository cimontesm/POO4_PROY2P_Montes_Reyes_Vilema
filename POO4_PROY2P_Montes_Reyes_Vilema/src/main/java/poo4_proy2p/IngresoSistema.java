/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;


/**
 *
 * @author cmontes
 */
public class IngresoSistema implements Initializable {
    
    @FXML
    private TextField tfusuario;
    
    @FXML
    private ImageView imagenprincipal;
    
    @FXML
    private Pane pane;
    
    @FXML
    private HBox hDatos;
    
    @FXML
    private VBox vLabels;
    
    @FXML
    private VBox vDatos;
    
    @FXML
    private TextField tfcontrasenia;
    
    @FXML
    private Button btnIniciarSesion;

    ArrayList<Usuario> usuarios;

   

    @FXML
    private VBox vLabelError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TO DO
    }
    


    @FXML
    public void iniciarSesion() {
        usuarios = new ArrayList<>();
        
        Usuario u1 = new Usuario("majoab", "majito1234");
        Usuario u2 = new Usuario("cx", "c21X");
        Usuario u3 = new Usuario("cmm", "12345");
        Usuario u4 = new Usuario("amp", "abcd");
        Usuario u5 = new Usuario("wasd", "ijkl");

        usuarios.add(u1);
        usuarios.add(u2);
        usuarios.add(u3);
        usuarios.add(u4);
        usuarios.add(u5);

        String ingresoU = tfusuario.getText();
        String ingresoC = tfcontrasenia.getText();
        boolean datosErroneos = true;

        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(ingresoU) && u.getContrasenia().equals(ingresoC)) {
                datosErroneos = false;
                try {
                    VentanaOpciones.mostrarVentanaOpciones(u);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
            } else if (datosErroneos){
                vLabelError.getChildren().clear();
                tfusuario.clear();
                tfcontrasenia.clear();
            
                Label lb = new Label("Datos Erróneos");
                lb.setTextFill(Color.PALEVIOLETRED); 
                lb.setFont(Font.font("System", FontWeight.BOLD, 15)); 
                lb.setStyle("-fx-background-color: POWDERBLUE");
                lb.setTextAlignment(TextAlignment.CENTER);

                vLabelError.getChildren().add(lb);
            }
        }
//        if (datosErroneos) {
//            vLabelError.getChildren().clear();
//            tfusuario.clear();
//            tfcontrasenia.clear();
//            
//            Label lb = new Label("Datos Erróneos");
//            lb.setTextFill(Color.PALEVIOLETRED); 
//            lb.setFont(Font.font("System", FontWeight.BOLD, 15)); 
//            lb.setStyle("-fx-background-color: POWDERBLUE");
//            lb.setTextAlignment(TextAlignment.CENTER);
//
//            vLabelError.getChildren().add(lb);
//        }
    }
}
