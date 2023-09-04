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
        try {
            Pedido.mostrarVentanaPedido();
        } catch(IOException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void cargarTopping() {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTopping();
    }

}
