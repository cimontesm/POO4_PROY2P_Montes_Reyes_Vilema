package poo4_proy2p;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    /**
     * La escena actual en la aplicación JavaFX. Esta variable se utiliza para
     * gestionar y cambiar la escena actual en la interfaz de usuario.
     */
    public static Scene scene;
    /**
     * La ventana principal de la aplicación JavaFX. Esta variable representa la
     * ventana principal de la aplicación y se utiliza para mostrar y gestionar
     * ventanas y diálogos.
     */
    public static Stage stage;

    /**
     * Inicializa la aplicación y muestra la ventana principal.
     *
     * @param stage El objeto Stage que representa la ventana principal de la
     * aplicación.
     * @throws IOException Si ocurre un error al cargar la interfaz gráfica de
     * la ventana principal.
     */
    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("heladeria.fxml"));
        Parent root = fxmlLoader.load();

        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Inicio");
        stage.show();

    }

    /**
     * Punto de entrada principal de la aplicación.
     *
     * @param args Los argumentos de línea de comandos (que generalmente no se
     * utilizan en una aplicación JavaFX).
     */
    public static void main(String[] args) {
        launch();

    }

}
