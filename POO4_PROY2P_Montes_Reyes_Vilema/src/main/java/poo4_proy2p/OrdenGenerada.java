/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.IOException;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Clase OrdenGenerada para controlar las ventanas
 *
 *
 * @author Cecilia Montes
 * @author Kimberly Reyes
 * @author Daniel Vilema
 */
public class OrdenGenerada {

    @FXML
    private Pane root;

    @FXML
    private ImageView fondo;

    @FXML
    private Label lblpedido;

    @FXML
    private Label lblcerrar;

    @FXML
    private ImageView foto;

    /**
     * Inicializa la ventana de confirmación de pedido. Genera un ID de pedido
     * único y muestra un mensaje con el número de pedido. Realiza una cuenta
     * regresiva para cerrar la ventana después de un tiempo determinado.
     */
    public void initialize() {
        String idPedido = generarID();
        lblpedido.setText("Tu pedido es el #" + idPedido + " te llamaremos cuando esté listo.");
        contarRegresivamente(Pedido.stage, lblcerrar, 5);
    }

    /**
     * Genera un ID único de pedido.
     *
     * @return Un ID de pedido único en formato de cadena.
     */
    public String generarID() {
        Random random = new Random();
        int numero = random.nextInt(10000);
        String id = String.format("%04d", numero);
        return id;
    }

    /**
     * Muestra la ventana final del proceso de pedido. Carga la interfaz de
     * usuario desde el archivo "ventanafinal.fxml" y la muestra en la escena
     * actual.
     *
     * @throws IOException Si ocurre un error al cargar la interfaz de usuario.
     */
    public static void mostrarVentanaFinal() throws IOException {
        FXMLLoader fxmLoader = new FXMLLoader(Pago.class.getResource("ventanafinal.fxml"));
        Parent root = fxmLoader.load();
        Pedido.scene = new Scene(root, 600, 400);
        Pedido.stage.setScene(Pedido.scene);
        Pedido.stage.setTitle("ArmaTuHelado7");
        Pedido.stage.show();

    }

    /**
     * Realiza una cuenta regresiva y cierra la ventana después de un tiempo
     * determinado.
     *
     * @param stage El objeto Stage que se cerrará al final de la cuenta
     * regresiva.
     * @param lblContador El objeto Label que muestra el contador de tiempo.
     * @param segundos El número total de segundos para la cuenta regresiva.
     */
    public static void contarRegresivamente(Stage stage, Label lblContador, int segundos) {
        Duration duracionTotal = Duration.seconds(segundos);
        final int[] segundosRestantes = {segundos}; // Variable final para el seguimiento de los segundos restantes

        // Crea un KeyFrame que se ejecutará cada segundo
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), e -> {
            segundosRestantes[0]--; // Decrementa el contador de segundos
            lblContador.setText("Cerrando en " + segundosRestantes[0] + " segundos");

            if (segundosRestantes[0] == 0) {
                stage.close(); // Cierra la ventana cuando llega a 0 segundos
            }
        });

        // Crea una línea de tiempo (Timeline) que ejecutará el KeyFrame
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(segundos); // Establece el número total de ciclos
        timeline.play(); // Inicia la línea de tiempo
    }
}
