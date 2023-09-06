/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poo4_proy2p;

/**
 * Interfaz Pagable que define el contrato para generar transacciones de pago.
 * Las clases que implementen esta interfaz deben proporcionar una implementación
 * para el método generarTransaccion().
 *
 * @author Cecilia Montes
 * @author Kimberly Reyes
 * @author Daniel Vilema
 */
public interface Pagable {
    /**
     * Genera una transacción de pago.
     *
     * @return Un objeto Pago que representa la transacción de pago generada.
     */
    public Pago generarTransaccion();
    
}
