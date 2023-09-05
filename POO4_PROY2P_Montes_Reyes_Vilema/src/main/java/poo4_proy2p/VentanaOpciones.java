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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
        Stage stage = new Stage();
        VBox root = new VBox();
        Scene scene = new Scene(root,300,150);
        Label lnombre = new Label(nombre);
        lnombre.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        Label lhorario = new Label("Horario: "+horario);
        Label lsegundos = new Label("Cerrando en 5 segundos...");
        
        root.getChildren().addAll(lnombre,lhorario,lsegundos);
        root.setPadding(new Insets(10));
        
        stage.setScene(scene);
        stage.setTitle("Informacion de local");
        stage.show();
        
        
        Thread duracion = new Thread(()->{
            int tiempo = 5; 
            while(tiempo>0){
                
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    System.out.println("Algo salio mal");
                }
                tiempo --;
                
                final int segundosF = tiempo;
                
                Platform.runLater(()->{
                    lsegundos.setText("Cerrando en "+segundosF+" restantes");

                });
               
            }
            Platform.runLater(()->{
                
                stage.close();
        
            });
            
        
        });
        
        duracion.setDaemon(true);
        duracion.start();
        
        
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
