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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
//        Thread pedidos = new Thread(()->{
//            while(true){
//                try{
//                    Platform.runLater(()->{
//                        ventanaPedidos();
//                    });
//                    Thread.sleep(5*60*1000);
//                }catch(InterruptedException e){
//                    e.printStackTrace();
//                }
//
//            }
//                
//        });
//        pedidos.setDaemon(true);
//        pedidos.start();
//        
//        
        btnLocales.setOnMouseClicked(e->{});
        ventanaLocales();
        
    }
    
    public void ventanaPedidos(){
        Stage stage = new Stage();
        VBox root = new VBox();
        Scene scene = new Scene(root,420,420);
        HBox h1 = new HBox();
        try(BufferedReader bf = new BufferedReader(new FileReader("pedidos.txt"))){
            String linea = bf.readLine();
            while(true){
                if(linea==null){
                    h1.getChildren().add(new Label(" "));
                }else{
                    h1.getChildren().add(new Label(linea));
                    root.getChildren().add(h1);
                    stage.setScene(scene);
                    stage.setTitle("Pedidos Generados");
                    stage.show();
                }
                linea = bf.readLine();

            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo.");
        } catch (IOException ex) {
            System.out.println("Algo salio mal.");
        }
        
        
    }   
    
    public void ventanaLocales(){
        Stage stage = new Stage();
        StackPane root = new StackPane();
        ImageView imgView = new ImageView();
        Image image = new Image("logo helado 1.png");
        imgView = new ImageView(image);
        root.getChildren().add(imgView);
        Scene scene = new Scene(root,400,400);
        stage.setTitle("Ubicaciones");
        stage.setScene(scene);
        stage.show();
        
    }
    
}
