   /**
 * Este módulo define las dependencias y configuraciones para la aplicación poo4_proy2p.
 * Utiliza JavaFX para la interfaz gráfica y requiere las bibliotecas 'javafx.controls' y 'javafx.fxml'.
 * Además, necesita 'java.base' para las dependencias básicas de Java.
 * Abre el paquete 'poo4_proy2p' para su uso con JavaFX y exporta el mismo paquete para que esté disponible externamente.
 */

module poo4_proy2p {
 
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens poo4_proy2p to javafx.fxml;
    exports poo4_proy2p;
}
