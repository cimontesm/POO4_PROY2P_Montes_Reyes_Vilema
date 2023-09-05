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

    public static Scene scene;
    public static Stage stage;
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

    public static void main(String[] args) {
        launch();
        Pedido pedido = new Pedido();
        BaseHelado base = new BaseHelado();
        Sabores sabor = new Sabores();
        Topping topping = new Topping();
        
    }

}
