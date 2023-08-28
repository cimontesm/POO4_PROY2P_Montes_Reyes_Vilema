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

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("heladería.fxml"));
        Parent root = fxmlLoader.load();
        
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    
    }

    public static void main(String[] args) {
        launch();
    }

}
