/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author cmontes
 */
public class EliminarSabor {

    private Stage stageEliminar;

    public void setStageEliminar(Stage stageEliminar) {
        this.stageEliminar = stageEliminar;
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

    @FXML
    public void confirmarElim() {

    }
}
