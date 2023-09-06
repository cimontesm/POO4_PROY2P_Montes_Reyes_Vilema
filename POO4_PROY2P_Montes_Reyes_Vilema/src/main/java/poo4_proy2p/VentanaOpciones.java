/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
//import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Clase VentanaOpciones me permite crear las primeras ventanas para encontrar
 * locales o realizar un pedido además nos ayuda como controlador para alguna
 * escena como pedido. nos ayuda a generar los contenidos de las ventanas con
 * las opciones indicadas.
 *
 * @author Cecilia Montes
 * @author Kimberly Reyes
 * @author Daniel Vilema
 */
public class VentanaOpciones implements Initializable {

    /**
     * Almacena información sobre el usuario actual que está interactuando con
     * la aplicación. Puede ser utilizado para rastrear la sesión y la
     * interacción del usuario.
     */
    public static Usuario usuario = null;

    /**
     * Almacena una lista de pedidos realizados por el usuario actual o por la
     * sesión actual. Los pedidos se agregan a esta lista para su posterior
     * procesamiento o seguimiento.
     */
    public static ArrayList<String> pedidos = new ArrayList<>();

    /**
     * Almacena una lista de componentes seleccionados por el usuario para armar
     * su pedido. Los componentes pueden incluir bases, sabores, toppings, etc.
     */
    public static ArrayList<Object> componentes = new ArrayList<>();

    /**
     * Almacena una lista de valores asociados a los componentes seleccionados
     * por el usuario. Cada valor corresponde al costo de un componente y se
     * utiliza para calcular el costo total del pedido.
     */
    public static ArrayList<Double> valoresAPagar = new ArrayList<>();

    /**
     * Muestra la ventana de opciones para el usuario especificado.
     *
     * @param usuario El usuario para el cual se muestra la ventana de opciones.
     * @throws IOException Si ocurre un error al cargar la interfaz de usuario.
     */
    public static void mostrarVentanaOpciones(Usuario usuario) throws IOException {
        VentanaOpciones.usuario = usuario;
        FXMLLoader fxmLoader = new FXMLLoader(VentanaOpciones.class.getResource("VentanaOpciones.fxml"));
        Parent root = fxmLoader.load();
        App.scene = new Scene(root, 600, 400);
        App.stage.setScene(App.scene);
        App.stage.setTitle("Bienvenidos");
        App.stage.show();
    }

    @FXML
    private Label lblBienvenida;

    @FXML
    private Button btnLocales;

    @FXML
    private Button btnPedido;

    /**
     * Inicializa la ventana de opciones para el usuario actual y carga pedidos
     * de forma periódica.
     *
     * @param url Recurso URL (no utilizado en este contexto).
     * @param rb ResourceBundle (no utilizado en este contexto).
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblBienvenida.setText("Bienvenido/a " + usuario.getUsuario());
//        crearVentanaPedidos();
        Stage stage = new Stage();
        ListView root = new ListView();
        Scene scene = new Scene(root, 420, 420);
//        HBox h1 = new HBox();
        Thread pedidos = new Thread(() -> {
            while (true) {
                try {
                    Platform.runLater(() -> {
                        cargarPedidos(stage, root, scene);
                    });
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        });
        pedidos.setDaemon(true);
        pedidos.start();

        stage.setScene(scene);
        stage.setTitle("Pedidos Generados");
        stage.show();

    }

    /**
     * Carga pedidos desde un archivo, muestra la lista de pedidos y actualiza
     * la interfaz de usuario.
     *
     * @param stage La ventana de Stage actual.
     * @param root El ListView donde se mostrarán los pedidos.
     * @param scene La escena de la ventana actual.
     */
    public void cargarPedidos(Stage stage, ListView root, Scene scene) {
        root.getItems().clear();
        try (BufferedReader bf = new BufferedReader(new FileReader("pedidos.txt"))) {
            String linea;
            pedidos.clear();
            while ((linea = bf.readLine()) != null) {
                String[] datos = linea.trim().split(",");
//                Pedido p = new Pedido(datos[0], Double.parseDouble(datos[2]), Integer.parseInt(datos[1]));
                pedidos.add(datos[1] + "," + datos[0]);

            }

        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo.");
        } catch (IOException ex) {
            System.out.println("Algo salio mal.");
        }

        ObservableList<String> items = FXCollections.observableArrayList(pedidos);
        root.setItems(items);
        root.setDisable(false);

    }

    /**
     * Abre una nueva ventana para mostrar el pedido actual y sus detalles. Esta
     * ventana permite a los usuarios revisar su pedido antes de confirmarlo.
     */
    @FXML
    public void mostrarVentanaPedido1() {
        try {
            BaseHelado.stage = new Stage();
            FXMLLoader fxmloader = new FXMLLoader(VentanaOpciones.class.getResource("pedido.fxml"));
            Parent root;
            root = fxmloader.load();
            BaseHelado.scene = new Scene(root, 600, 400);
            BaseHelado.stage.setScene(BaseHelado.scene);
            BaseHelado.stage.setTitle("ArmaTuHelado");
            BaseHelado.stage.show();
        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Muestra ubicaciones de locales en un mapa y actualiza su estado de forma
     * periódica. Utiliza imágenes para representar las ubicaciones de los
     * locales y muestra información detallada al hacer clic en cada ubicación.
     * Este método inicia un hilo de ejecución para actualizar periódicamente la
     * información de los locales.
     *
     * @param root El nodo raíz en el que se mostrarán las ubicaciones de los
     * locales.
     */
    private void mostrarLocales(AnchorPane root) {
        ArrayList<Local> locales = hallarLocales();
        ArrayList<ImageView> ubicaciones = new ArrayList<>();
        Thread hilo = new Thread(() -> {
            for (Local local : locales) {
                Platform.runLater(() -> {
                    try (FileInputStream input = new FileInputStream(new File("src/main/resources/poo4_proy2p/logo1.png"))) {
                        Image image = new Image(input);

                        ImageView punto = new ImageView(image);
                        punto.setFitWidth(200);
                        punto.setFitHeight(200);
                        punto.setLayoutX(local.getCoordX());
                        punto.setLayoutY(local.getCoordY());
                        if (!ubicaciones.contains(punto)) {
                            ubicaciones.add(punto);
                            root.getChildren().addAll(punto);
                        }
                        punto.setOnMouseClicked(event -> mostrarInfo(local.nombre, local.horario));

                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

                int tiempo = new Random().nextInt(10) + 1;
                try {
                    Thread.sleep(tiempo * 1000);
                } catch (InterruptedException e) {
                    System.out.println("Algo salio mal");
                }
            }

        });
        hilo.setDaemon(true);
        hilo.start();

    }

    /**
     * Recupera la información de locales a partir de un archivo y la almacena
     * en una lista. Lee un archivo de texto llamado "locales.txt" que contiene
     * información sobre los locales, como coordenadas geográficas, nombres y
     * horarios. Luego, crea objetos Local a partir de los datos leídos y los
     * agrega a una lista de locales.
     *
     * @return Una lista de objetos Local que representan los locales
     * encontrados en el archivo.
     */
    public ArrayList<Local> hallarLocales() {
        ArrayList<Local> locales = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader("locales.txt"))) {
            String linea = bf.readLine();
            while (linea != null) {

                String datosP[] = linea.trim().split(",");
                double coordX = Double.parseDouble(datosP[0]);
                double coordY = Double.parseDouble(datosP[1]);
                String nombre = datosP[2];
                String horario = datosP[3];
                Local local = new Local(coordX, coordY, nombre, horario);
                locales.add(local);

                linea = bf.readLine();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");;
        } catch (IOException ex) {
            System.out.println("Algo salio mal");
        }

        return locales;

    }

    /**
     * Muestra información detallada sobre un local, incluyendo su nombre,
     * horario y un temporizador de cierre.
     *
     * @param nombre El nombre del local que se mostrará en la ventana de
     * información.
     * @param horario El horario del local que se mostrará en la ventana de
     * información.
     */
    public void mostrarInfo(String nombre, String horario) {
        Stage stage = new Stage();
        VBox root = new VBox();
        Scene scene = new Scene(root, 300, 150);
        Label lnombre = new Label(nombre);
        lnombre.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        Label lhorario = new Label("Horario: " + horario);
        Label lsegundos = new Label("Cerrando en 5 segundos...");

        root.getChildren().addAll(lnombre, lhorario, lsegundos);
        root.setPadding(new Insets(10));

        stage.setScene(scene);
        stage.setTitle("Informacion de local");
        stage.show();

        Thread duracion = new Thread(() -> {
            int tiempo = 5;
            while (tiempo > 0) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Algo salio mal");
                }
                tiempo--;

                final int segundosF = tiempo;

                Platform.runLater(() -> {
                    lsegundos.setText("Cerrando en " + segundosF + " restantes");

                });

            }
            Platform.runLater(() -> {

                stage.close();

            });

        });

        duracion.setDaemon(true);
        duracion.start();

    }

    /**
     * Abre una ventana que muestra un mapa y la ubicación de varios locales.
     * Cada local se representa como un punto en el mapa con su nombre y
     * horario. Además, se inicia un hilo que actualiza periódicamente la
     * ubicación de los locales.
     *
     * @param event El evento de acción que desencadena la apertura del mapa.
     */
    @FXML
    private void verMapa(ActionEvent event) {

        Stage stage = new Stage();
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 800, 600);
        ImageView imageView = null;

        try (FileInputStream input = new FileInputStream(new File("src/main/resources/poo4_proy2p/mapa2.png"))) {
            Image image = new Image(input);
            imageView = new ImageView(image);
            imageView.setFitWidth(800);
            imageView.setFitHeight(600);

        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");
        } catch (IOException ex) {
            System.out.println("Algo salio mal");
        }

        root.getChildren().add(imageView);
        mostrarLocales(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Calcula y muestra el valor total a pagar en la etiqueta especificada.
     *
     * @param lblValor La etiqueta en la que se mostrará el valor total a pagar.
     */
    public static void cargarValorAPagar(Label lblValor) {
        double suma = 0.0;
//        DecimalFormat df = new DecimalFormat("#.##");

        for (Double valor : VentanaOpciones.valoresAPagar) {
            suma += valor;
        }
//        String numFormat = df.format(suma);

//        lblValor.setText("Valor a pagar: "+Double.parseDouble(numFormat));
        lblValor.setText("Valor a pagar: " + suma);

    }

}
