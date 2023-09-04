/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cmontes
 */
public class VentanaOpciones implements Initializable {
    public static Usuario usuario = null;
    public static ArrayList<Pedido> pedidos = new ArrayList<>();
    
    public static void mostrarVentanaOpciones(Usuario usuario) throws IOException{
        VentanaOpciones.usuario = usuario;
        FXMLLoader fxmLoader = new FXMLLoader(VentanaOpciones.class.getResource("VentanaOpciones.fxml"));
        Parent root = fxmLoader.load();
        App.scene = new Scene(root,600,400);
        App.stage.setScene(App.scene);
        App.stage.setTitle("Bienvenidos");
        App.stage.show();
    }
    
    @FXML
    private Label lblBienvenida;
   
    @FXML
    private Button btnLocales;
    
    @FXML
    private Button btnPedido;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hallarLocales();
        lblBienvenida.setText("Bienvenido/a "+usuario.getUsuario());
//        crearVentanaPedidos();
        Stage stage = new Stage();
        ListView root = new ListView();
        Scene scene = new Scene(root,420,420);
//        HBox h1 = new HBox();
        Thread pedidos = new Thread(()->{
            while(true){
                try{
                    Platform.runLater(()->{
                        cargarPedidos(stage,root,scene);
                    });
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
                
        });
        pedidos.setDaemon(true);
        pedidos.start();
        
        stage.setScene(scene);
        stage.setTitle("Pedidos Generados");
        stage.show();
        
//        btnLocales.setOnMouseClicked(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent t){
//                
//            }
//        });
        
    }
    
    public void cargarPedidos(Stage stage, ListView root, Scene scene){
        root.getItems().clear();
        try(BufferedReader bf = new BufferedReader(new FileReader("pedidos.txt"))){
            String linea;
            pedidos.clear();
            while((linea=bf.readLine())!= null){
                String[] datos = linea.trim().split(",");
                Pedido p = new Pedido(datos[0],Double.parseDouble(datos[2]),datos[1]);
                pedidos.add(p);

            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo.");
        } catch (IOException ex) {
            System.out.println("Algo salio mal.");
        }
        
        ObservableList<Pedido> items = FXCollections.observableArrayList(pedidos);
        root.setItems(items);
        root.setDisable(false);
            
        
    }
    
    @FXML
    public void mostrarVentanaPedido1(){
        try {
            BaseHelado.stage = new Stage();
            FXMLLoader fxmloader = new FXMLLoader(VentanaOpciones.class.getResource("pedido.fxml"));
            Parent root;
            root = fxmloader.load();
            BaseHelado.scene = new Scene(root,600,400);
            BaseHelado.stage.setScene(BaseHelado.scene);
            BaseHelado.stage.setTitle("ArmaTuHelado");
            BaseHelado.stage.show();
        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void mostrarMapa(ActionEvent event) {
        
        
        
    }
    
    
    
    public void hallarLocales(){
        ArrayList<Local> locales = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader("locales.txt"))){
            String linea = bf.readLine();
            while(linea!=null){
                
                String datosP[] = linea.trim().split(",");
                double coordX = Double.parseDouble(datosP[0]);
                double coordY = Double.parseDouble(datosP[1]);
                String nombre = datosP[2];
                String horario = datosP[3];
                Local local = new Local(coordX,coordY,nombre,horario);
                locales.add(local);
                
                linea = bf.readLine();
            }
            
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    
}
