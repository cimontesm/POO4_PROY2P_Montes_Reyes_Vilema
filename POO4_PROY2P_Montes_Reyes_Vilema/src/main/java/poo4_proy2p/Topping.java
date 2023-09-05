
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author cmontes
 */
public class Topping implements Initializable {

    String nombre;
    double precio;
    ArrayList<CheckBox> cboxes;
    ArrayList<Double> precios;

    public Topping(){
        
    }
    
    public Topping(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @FXML
    private Pane root;

    @FXML
    private ImageView fondo;

    @FXML
    private VBox vcb;

    @FXML
    private CheckBox cb1;

    @FXML
    private CheckBox cb2;

    @FXML
    private CheckBox cb3;

    @FXML
    private CheckBox cb4;

    @FXML
    private CheckBox cb5;

    @FXML
    private CheckBox cb6;

    @FXML
    private Label lblValor;

    @FXML
    private Button btnContinuar;

    @FXML
    public void continuar(){
        
        ArrayList<Boolean> lbooleanos = elementoSeleccionado(cboxes);
        int longitud = Math.min(cboxes.size(), lbooleanos.size());
        
        for (int i=0;i<longitud;i++){
            CheckBox elemento = cboxes.get(i);
            boolean bool = lbooleanos.get(i);
            double precio = precios.get(i);
            
            if (bool){
                VentanaOpciones.componentes.add(elemento.getUserData());
                VentanaOpciones.valoresAPagar.add(precio);
                
            }
            
        }
        
        try {
            Pedido.mostrarVentanaPedido();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void cargarTopping() {
        cboxes = new ArrayList<>();
        precios = new ArrayList<>();
        
        try ( BufferedReader br = new BufferedReader(new FileReader("toppings.txt"))) {
            String linea;
            int i = 1;
            while ((linea = br.readLine()) != null && i <= 6) {
                String[] items = linea.split(",");
                if (items.length >= 2) {
                    String nombreTopping = items[0];
                    String precioTopping = items[1];

                    String checkBoxName = "cb" + i;
                    CheckBox checkBox = (CheckBox) getClass().getDeclaredField(checkBoxName).get(this);
                    checkBox.setText(nombreTopping + " - " + precioTopping);
                    checkBox.setUserData(new Topping(nombreTopping,Double.parseDouble(precioTopping)));
                    cboxes.add(checkBox);
                    precios.add(Double.parseDouble(precioTopping));
                    
                    i++;
                }

            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    
    public static void cargarVentanaTopping() throws IOException{
        FXMLLoader fxmLoader = new FXMLLoader(Sabores.class.getResource("toppings.fxml"));
        Parent root = fxmLoader.load();
        BaseHelado.scene = new Scene(root, 600, 400);
        BaseHelado.stage.setScene(BaseHelado.scene);
        BaseHelado.stage.setTitle("ArmaTuHelado3");
        BaseHelado.stage.show();
    }
    
    public ArrayList<Boolean> elementoSeleccionado(ArrayList<CheckBox> cboxes){
        ArrayList<Boolean> retorno = new ArrayList<>();
        
        for (CheckBox c : cboxes){
            if (c.isSelected()){
                retorno.add(true);
            } else {
                retorno.add(false);
            }
        }
        return retorno;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTopping();
        
        VentanaOpciones.cargarValorAPagar(lblValor);
        
    }
    
}
