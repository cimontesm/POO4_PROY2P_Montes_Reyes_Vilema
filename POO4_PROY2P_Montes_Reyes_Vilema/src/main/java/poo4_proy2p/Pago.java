/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    
    
}
