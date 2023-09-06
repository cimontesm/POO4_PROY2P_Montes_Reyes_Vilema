/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
//import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
//import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Clase EliminarSabor Nos ayuda a eliminar los sabores de la ventana según lo
 * elija el usuario.
 *
 *
 * @author Cecilia Montes
 * @author Kimberly Reyes
 * @author Daniel Vilema
 */
public class EliminarSabor {

    private Stage stageEliminar;

    /**
     * Establece el objeto Stage que se utilizará para realizar la eliminación
     * de un elemento.
     *
     * @param stageEliminar El objeto Stage que se utilizará para realizar la
     * eliminación de un elemento.
     */
    public void setStageEliminar(Stage stageEliminar) {
        this.stageEliminar = stageEliminar;
    }

//    public EliminarSabor() {
////        lv = new ListView<>();
//    }
    @FXML
    private Pane rootelim;

    @FXML
    private HBox hbotoneselim;

    @FXML
    private Button btnconf3;

    @FXML
    private Button btncancelar3;

    /**
     * Cancela la operación actual cerrando el objeto Stage utilizado para
     * eliminar un elemento, si está disponible.
     */
    @FXML
    public void cancelar3() {
        if (stageEliminar != null) {
            stageEliminar.close();
        }
    }

//    ListView<String> listView = lv;
    /**
     * Confirma la eliminación de un elemento seleccionado y realiza las
     * operaciones necesarias.
     *
     * @throws IOException Si ocurre un error durante la eliminación.
     */
    @FXML
    public void confirmarElim() throws IOException {
        String elementoSeleccionado = Pedido.p.getLv().getSelectionModel().getSelectedItem();
        ArrayList<Boolean> booleanos = new ArrayList<>();
        ObservableList<String> items = Pedido.p.getLv().getItems();

        if (elementoSeleccionado != null) {
            int longitud = Math.min(Pedido.p.getLv().getItems().size(), booleanos.size());
            int trues = 0;

            for (String item : items) {
                item = Pedido.p.getLv().getSelectionModel().getSelectedItem();

                boolean esSabor = Pedido.esSabor(item);
                booleanos.add(esSabor);
                if (esSabor) {
                    trues++;
                }

            }

            if (trues == 1) {
                for (int i = 0; i < longitud; i++) {
                    boolean bool = booleanos.get(i);
                    String elemento = items.get(i);
                    if (bool) {
                        items.remove(elemento);
                        Pedido.p.getLv().setItems(items);
                        VentanaOpciones.componentes.remove(i);
                        VentanaOpciones.valoresAPagar.remove(i);
                        stageEliminar.close();
                    }

                }

            } else {
                System.out.println("debe de haber al menos un sabor");
                stageEliminar.close();
            }

        } else {
            stageEliminar.close();
        }

    }
}
