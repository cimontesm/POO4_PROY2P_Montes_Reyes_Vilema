/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author cmontes
 */
public class BaseHelado implements Initializable {
    String nombre; 
    double precio;
    
    public BaseHelado(){
        
    }

    public BaseHelado(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
    
//    public static void mostrarVentanaPedido1() throws IOException{
//        Stage stage = new Stage();
//        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("pedido.fxml"));
//        Parent root = fxmloader.load();
//        scene = new Scene(root,600,400);
//        stage.setScene(scene);
//        stage.setTitle("ArmaTuHelado");
//        stage.show();
//        
//    }
    
    public static ArrayList<BaseHelado> cargarBases(){
        ArrayList<BaseHelado> bh = new ArrayList<>();
        try(BufferedReader bf=new BufferedReader(new FileReader("bases.txt"))){
            String linea;
            while((linea=bf.readLine())!=null){
                String[] datosp =linea.split(",");
                bh.add(new BaseHelado(datosp[0],Double.parseDouble(datosp[1])));
                
            }

        }catch(FileNotFoundException ex1){
            System.out.println(ex1.getMessage());
            
        }catch(IOException ex2){
            System.out.println(ex2.getMessage());
        }
        
        return bh;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //PARA CAMBIAR DE ESCENA
//        Usuario u=null;
//        Sabores.usuario = u;
//        try {
//                    VentanaOpciones.mostrarVentanaOpciones(u);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
        
        ArrayList<BaseHelado> bases = BaseHelado.cargarBases();
        ImageView imgView = null;
        
        for (BaseHelado base : bases){
            try (FileInputStream input = new FileInputStream("src/main/resources/poo4_proy2p/"+base.getNombre()+".jpg")){
                Image image = new Image(input,30,30,false,false);
                imgView = new ImageView(image);
                
            } catch (IOException e){
                System.out.println("No se encuentra la imagen");
            }
//            Label l1 = new Label(base.getNombre());
            Label l2 = new Label(String.valueOf(base.getPrecio()));
            
            ToggleButton tb = new ToggleButton(base.getNombre());
            tb.setGraphic(imgView);
            
            VBox VBase = new VBox();
            VBase.getChildren().addAll(tb,l2);
            VBase.setAlignment(Pos.CENTER);
            VBase.setSpacing(10);
            VBase.setPadding(new Insets(10,15,10,15));
            seccionBases.getChildren().add(VBase);
            
        }
        
    }
    
    @FXML
    private Button btnContinuar;
    
    @FXML
    private Label lblValor;
    
    @FXML
    private Pane rootH;
    
    @FXML
    private FlowPane seccionBases;
    
    @FXML
    private Label lblmensaje;
    
    @FXML
    public void continuar(){
        
    }
}
