/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

/**
 * Excepcion personalizada
 *
 *
 * @author Cecilia Montes
 * @author Kimberly Reyes
 * @author Daniel Vilema
 */
public class IncompleteStageException extends Exception{
    /**
     * Crea una nueva instancia de la excepción con un mensaje específico.
     *
     * @param n El mensaje que describe la razón de la excepción.
     */
    public IncompleteStageException(String n){
        super(n);
    }
    
}
