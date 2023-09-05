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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author cmontes
 */
public class BaseHelado implements Initializable {
    String nombre; 
    double precio;
    ArrayList<ToggleButton> tgbuttons;
    ArrayList<Double> precios;
    public static Stage stage;
    public static Scene scene;
    
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
        
        ArrayList<BaseHelado> bases = BaseHelado.cargarBases();
        ImageView imgView = null;
        ToggleButton tb = null;
        tgbuttons = new ArrayList<>();
        ToggleGroup tg = new ToggleGroup();
        precios = new ArrayList<>();
        
        for (BaseHelado base : bases){
            try (FileInputStream input = new FileInputStream("src/main/resources/poo4_proy2p/"+base.getNombre()+".jpg")){
                Image image = new Image(input,50,50,false,false);
                imgView = new ImageView(image);
                
            } catch (IOException e){
                System.out.println("No se encuentra la imagen");
            }
//            Label l1 = new Label(base.getNombre());
            Label l2 = new Label(String.valueOf(base.getPrecio()));
            precios.add(base.getPrecio());
            l2.setStyle("-fx-text-fill: purple; -fx-font-weight: bold; -fx-font-size: 15px;");
            
            tb = crearToggleButton(base.getNombre(),imgView,tg);
            tgbuttons.add(tb);
            
            VBox VBase = new VBox();
            VBase.getChildren().addAll(tb,l2);
            VBase.setAlignment(Pos.CENTER);
            VBase.setSpacing(10);
            VBase.setPadding(new Insets(10,15,10,15));
            seccionBases.getChildren().add(VBase);
            
        }
        
        
    }
    
    public ToggleButton crearToggleButton(String texto, ImageView imgView, ToggleGroup tg){
        ToggleButton tb = new ToggleButton(texto);
        tb.setToggleGroup(tg);
        
        DropShadow brillo = new DropShadow();
        brillo.setColor(Color.BLUE);
        brillo.setWidth(40);
        brillo.setHeight(40);
        
        tb.setStyle("-fx-text-fill: purple; -fx-font-weight: bold; -fx-font-size: 15px; -fx-background-color: #E0FFFF; -fx-border-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0.5, 3, 3);");
        tb.setGraphic(imgView);
        tb.setMinWidth(130);
        tb.setMinHeight(100);
        
        tb.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected){
                tb.setEffect(brillo);
            } else {
                tb.setEffect(null);
            }
        });
        
        return tb;
    }
    
    public ArrayList<Boolean> elementoSeleccionado(ArrayList<ToggleButton> tgbuttons){
        ArrayList<Boolean> retorno = new ArrayList<>();
        
        for (ToggleButton t : tgbuttons){
            if (t.isSelected()){
                retorno.add(true);
            } else {
                retorno.add(false);
            }
        }
        return retorno;
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
        ArrayList<Boolean> lbooleanos = elementoSeleccionado(tgbuttons);
        int longitud = Math.min(tgbuttons.size(), lbooleanos.size());
        boolean contieneTrue = false;
        
        for (int i=0;i<longitud;i++){
            ToggleButton elemento = tgbuttons.get(i);
            boolean bool = lbooleanos.get(i);
            double precio = precios.get(i);
            
            if (bool){
                VentanaOpciones.componentes.add(new BaseHelado(elemento.getText(),precio));
                VentanaOpciones.valoresAPagar.add(precio);
                double suma = 0.0;
                
                for (Double valor : VentanaOpciones.valoresAPagar){
                    suma += valor;
                }
                lblValor.setText("Valor a pagar: "+suma);
            }
            
        }
        
        for (boolean bool : lbooleanos){
            if (bool){
                contieneTrue = true;
                break;
            }
        }
        
        if (!contieneTrue){
            try {
                throw new IncompleteStageException("Debe seleccionar al menos una opcion para continuar");
            } catch (IncompleteStageException ex1) {
                System.out.println(ex1.getMessage());
            }
            lblmensaje.setText("Debe seleccionar al menos una opcion para continuar");
            
        } else {
            try {
                Sabores.mostrarVentanaSabores();
            } catch(IOException ex2){
                System.out.println(ex2.getMessage());
//                ex.printStackTrace();
            }
            
        }
        
        
        
    }
}
