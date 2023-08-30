module poo4_proy2p {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens poo4_proy2p to javafx.fxml;
    exports poo4_proy2p;
}
