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
 * Clase CancelarPedido Nos ayuda a cerrar las ventanas en caso de cancelar los
 * pedidos.
 *
 * @author Cecilia Montes
 * @author Kimberly Reyes
 * @author Daniel Vilema
 */
public class CancelarPedido {

    private Stage stageCancelar;

    @FXML
    private HBox hbtns;

    @FXML
    private Button btnconf2;

    @FXML
    private Button btncancelar2;

    /**
     * Establece el objeto Stage que se utilizará para cancelar una operación.
     *
     * @param stageCancelar El objeto Stage que se utilizará para cancelar una
     * operación.
     */
    public void setStageCancelar(Stage stageCancelar) {
        this.stageCancelar = stageCancelar;
    }

    /**
     * Cancela una operación cerrando el objeto Stage proporcionado, si está
     * disponible.
     */
    @FXML
    public void cancelar2() {
        if (stageCancelar != null) {
            stageCancelar.close();
        }
    }

    /**
     * Confirma y ejecuta la cancelación de una operación. Cierra las ventanas
     * asociadas, limpia las listas de componentes y valores a pagar.
     */
    @FXML
    public void confirmarCancel() {
        BaseHelado.stage.close();
        stageCancelar.close();

        VentanaOpciones.componentes.clear();
        VentanaOpciones.valoresAPagar.clear();

    }

}
