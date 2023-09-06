/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author cmontes
 */
public class CancelarPedido {

    private Stage stageCancelar;

    @FXML
    private HBox hbtns;

    @FXML
    private Button btnconf2;

    @FXML
    private Button btncancelar2;

    public void setStageCancelar(Stage stageCancelar) {
        this.stageCancelar = stageCancelar;
    }

    @FXML
    public void cancelar2() {
        if (stageCancelar != null) {
            stageCancelar.close();
        }
    }

    @FXML
    public void confirmarCancel() {
        BaseHelado.stage.close();
        stageCancelar.close();
        
        VentanaOpciones.componentes.clear();
        VentanaOpciones.valoresAPagar.clear();
        
    }

}
