/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author cmontes
 */
public class BaseHelado {
    String nombre; 
    double precio;
    
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
