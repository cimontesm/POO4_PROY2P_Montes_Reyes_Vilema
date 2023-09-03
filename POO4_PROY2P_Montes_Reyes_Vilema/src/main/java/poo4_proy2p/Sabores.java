/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author cmontes
 */
public class Sabores {
    String nombre;
    double precio;
    
    @FXML
    private Pane root;
    
    @FXML
    private ImageView fondo;
    
    @FXML
    private HBox hbox;
    
    @FXML
    private VBox v1;
    
    @FXML
    private VBox v2;
    
    @FXML
    private ComboBox cbsabor1;
    
    @FXML
    private ComboBox cbsabor2;
    
    @FXML
    private Label lblValor;
            
    @FXML
    private Button btnContinuar;
    
    @FXML
    public void continuar(){
        
    }
    
    public void cargarcb(){
        try(BufferedReader br = new BufferedReader(new FileReader("sabores.txt")) ){
            String linea;
            while((linea=br.readLine())!=null){
                String items = linea.replace(",", "-");
                cbsabor1.getItems().add(items);
                cbsabor2.getItems().add(items);
               
            }
            
            
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    static Usuario usuario;
    
    public static void mostrarVentanaSabores(Usuario usuario) throws IOException{
        Sabores.usuario = usuario;
        FXMLLoader fxmLoader = new FXMLLoader(VentanaOpciones.class.getResource("sabores.fxml"));
        Parent root = fxmLoader.load();
        App.scene = new Scene(root,600,400);
        App.stage.setScene(App.scene);
        App.stage.setTitle("ArmaTuHelado2");
        App.stage.show();
    }
}
