
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author cmontes
 */
public class Pedido implements Initializable {
    public static Scene scene;
    public static Stage stage;
    String nombre;
    double total;
    String id;
  
    public Pedido(){
        stage = new Stage();
    }
    
    public Pedido(String nombre, double total, String id) {
        this.nombre = nombre;
        this.total = total;
        this.id = id;
    }
    
    @FXML
    private Pane root;
    
    @FXML
    private ImageView fondo;
    
    @FXML
    private ListView lv;
    
    @FXML
    private Label lblValor;
    
    @FXML
    private Button btnConfirmar;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Button btnEliminar;
    
    @FXML
    private Pane rootc;

    
    @FXML
    public void confirmar(){
        try {
            Pago.mostrarVentanaPago();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        cargarListView();
        
        VentanaOpciones.cargarValorAPagar(lblValor);
        
    }
    
    public void cargarListView(){
        ArrayList<String> elementos = new ArrayList<>();
        
        for (Object componente : VentanaOpciones.componentes){
            if (componente instanceof BaseHelado){
                BaseHelado bh = (BaseHelado) componente;
                elementos.add("Base: "+bh.getNombre());
                
            } else if (componente instanceof Sabores){
                Sabores s = (Sabores) componente;
                elementos.add("Sabor: "+s.getNombre());
                
            } else if (componente instanceof Topping){
                Topping tp = (Topping) componente;
                elementos.add("Topping: "+tp.getNombre());
                
            }
            
        }
        
        ObservableList<String> items = FXCollections.observableArrayList(elementos);
        lv.setItems(items);
        lv.setDisable(false);
        
    }
    
    @FXML
    public void cancelar() throws IOException{
        FXMLLoader fxmLoader = new FXMLLoader(Pedido.class.getResource("cancelarcompra.fxml"));
        Parent root = fxmLoader.load();
        Pedido.scene = new Scene(root, 400, 200);
        Pedido.stage.setScene(Pedido.scene);
        Pedido.stage.setTitle("Mensaje");
        Pedido.stage.show();
        
    }
    
    @FXML
    public void eliminar() throws IOException{
        FXMLLoader fxmLoader = new FXMLLoader(Pedido.class.getResource("eliminarsabor.fxml"));
        Parent root = fxmLoader.load();
        Pedido.scene = new Scene(root, 400, 200);
        Pedido.stage.setScene(Pedido.scene);
        Pedido.stage.setTitle("Mensaje");
        Pedido.stage.show();
        
    }
    
    @Override
    public String toString(){
        return nombre+","+id;
    }
    
    public static void mostrarVentanaPedido() throws IOException{
        FXMLLoader fxmLoader = new FXMLLoader(Topping.class.getResource("pedidogen.fxml"));
        Parent root = fxmLoader.load();
        BaseHelado.scene = new Scene(root,600,400);
        BaseHelado.stage.setScene(BaseHelado.scene);
        BaseHelado.stage.setTitle("ArmaTuHelado4");
        BaseHelado.stage.show();
    }
    
    
    
}
