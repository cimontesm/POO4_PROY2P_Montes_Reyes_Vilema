/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TO DO
    }
    
    @FXML
    public void iniciarSesion(){
        Usuario u1 = new Usuario("majoabril", "majito1234");
        Usuario u2 = new Usuario("cx", "c21X");
    }
}
