/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author cmontes
 */
public class EliminarSabor extends Pedido{

    private Stage stageEliminar;

    public void setStageEliminar(Stage stageEliminar) {
        this.stageEliminar = stageEliminar;
    }

    public EliminarSabor(){
        
    }
    @FXML
    private Pane rootelim;

    @FXML
    private HBox hbotoneselim;

    @FXML
    private Button btnconf3;

    @FXML
    private Button btncancelar3;

    @FXML
    public void cancelar3() {
        if (stageEliminar != null) {
            stageEliminar.close();
        }
    }

    ListView<String> listView = lv;
    
    
    
    @FXML
    public void confirmarElim() throws IOException {
        cargarListView();
        String elementoSeleccionado = listView.getSelectionModel().getSelectedItem();

        if (elementoSeleccionado != null) {
           
            boolean esSabor = Pedido.esSabor(elementoSeleccionado);

            if (esSabor) {
                
                ObservableList<String> items = lv.getItems();

               
                if (items.size() > 1) {
                   
                    items.remove(elementoSeleccionado);
                } else {
                    System.out.println("debe de haber al menos un sabor");
                }
            }
        }
    }
}
