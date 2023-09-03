/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;

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
    
    @FXML
    private ImageView fondo;
    
    @FXML
    private TextFlow textflow;
    
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
}
