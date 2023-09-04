/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cmontes
 */
public class VentanaOpciones implements Initializable {
    public static Usuario usuario = null;
    
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
        lblBienvenida.setText("Bienvenido/a "+usuario.getUsuario());
        Thread pedidos = new Thread(()->{
            while(true){
                try{
                    Platform.runLater(()->{
                        ventanaPedidos();
                    });
                    Thread.sleep(5*60*1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
                
        });
        pedidos.setDaemon(true);
        pedidos.start();
        
        btnLocales.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){
                
            }
        });
        
//        btnPedido.setOnMouseClicked(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent t){
//                try {
//                    BaseHelado.mostrarVentanaPedido1();
//                } catch (IOException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            }
//        });
        
//        
    }
    
    public void ventanaPedidos(){
        Stage stage = new Stage();
        VBox root = new VBox();
        Scene scene = new Scene(root,420,420);
        HBox h1 = new HBox();
        try(BufferedReader bf = new BufferedReader(new FileReader("pedidos.txt"))){
            String linea;
            ArrayList<String> pedidos = new ArrayList<>();
            if ((linea=bf.readLine()) == null){
                h1.getChildren().add(new Label(" "));
            } else {
                while((linea=bf.readLine())!= null){
//                if(linea==null){
//                    h1.getChildren().add(new Label(" "));
//                }else{
                    pedidos.add(linea);
//                    h1.getChildren().add(new Label(linea));
//                    root.getChildren().add(h1);
//                    stage.setScene(scene);
//                    stage.setTitle("Pedidos Generados");
//                    stage.show();
//                }
//                linea = bf.readLine();

                }
            }
            for (String pedido : pedidos){
                h1.getChildren().add(new Label(pedido));
                
            }
            root.getChildren().add(h1);
            
            stage.setScene(scene);
            stage.setTitle("Pedidos Generados");
            stage.show();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo.");
        } catch (IOException ex) {
            System.out.println("Algo salio mal.");
        }
        
        
    }
    
    @FXML
    public void mostrarVentanaPedido1(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmloader = new FXMLLoader(VentanaOpciones.class.getResource("pedido.fxml"));
            Parent root;
            root = fxmloader.load();
            Scene scene = new Scene(root,600,400);
            stage.setScene(scene);
            stage.setTitle("ArmaTuHelado");
            stage.show();
        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

  
    private void mostrarLocales(AnchorPane root) {
        ArrayList<Local> locales = hallarLocales();
        ArrayList<ImageView> ubicaciones = new ArrayList<>();
        Thread hilo = new Thread(()->{
            for(Local local:locales){
                Platform.runLater(()->{
                    try(FileInputStream input = new FileInputStream(new File("src/main/resources/poo4_proy2p/logo1.png"))){
                        Image image = new Image(input);
                        
                        ImageView punto = new ImageView(image);
                        punto.setFitWidth(300);
                        punto.setFitHeight(300);
                        punto.setLayoutX(local.getCoordX());
                        punto.setLayoutY(local.getCoordY());
                        if (!ubicaciones.contains(punto)) {
                            ubicaciones.add(punto);
                            root.getChildren().addAll(punto);
                        }
                        punto.setOnMouseClicked(event->mostrarInfo(local.nombre,local.horario));
                        
                        
                        
                        
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                
                int tiempo = new Random().nextInt(10)+1;
                try{
                    Thread.sleep(tiempo*1000);
                }catch(InterruptedException e){
                    System.out.println("Algo salio mal");
                }
            }
        
        });
        hilo.setDaemon(true);
        hilo.start();
        
        
    }
    
    
    
    public ArrayList<Local> hallarLocales(){
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
            System.out.println("No se encontro el archivo");;
        } catch (IOException ex) {
            System.out.println("Algo salio mal");
        }
        
        return locales;

    }
    
    public void mostrarInfo(String nombre, String horario){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informacion de Local");
        alert.setHeaderText(nombre);
        //alert.setContentText("Horario: "+horario);
        
        Thread duracion = new Thread(()->{
            int tiempo = 5; 
            while(tiempo>0){
                String mensaje = "Cerrando en "+tiempo+" segundos.";
                Platform.runLater(()->{
                    alert.setContentText("Horario: "+horario+"\n"+mensaje);

                });
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    System.out.println("Algo salio mal");
                }
                tiempo --;
               
            }
            Platform.runLater(()->{
                
                alert.close();
        
            });
            
        
        });
        
        duracion.setDaemon(true);
        duracion.start();
        alert.showAndWait();
        
        
    }

    @FXML
    private void verMapa(ActionEvent event) {
            
        Stage stage = new Stage();
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root,800,600);
        ImageView imageView = null;

        try(FileInputStream input = new FileInputStream(new File("src/main/resources/poo4_proy2p/mapa2.png"))){
            Image image = new Image(input,800,600,true,true);
            imageView = new ImageView(image);
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");;
        } catch (IOException ex) {
            System.out.println("Algo salio mal");
        }
        
        root.getChildren().add(imageView);
        mostrarLocales(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
