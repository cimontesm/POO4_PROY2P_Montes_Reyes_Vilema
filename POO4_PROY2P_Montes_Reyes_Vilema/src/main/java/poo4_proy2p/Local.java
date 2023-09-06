/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

/**
 * Clase Local poder crear instancias según la lectura del archivo
 *
 *
 * @author Cecilia Montes
 * @author Kimberly Reyes
 * @author Daniel Vilema
 */
public class Local {

    private double coordX;
    private double coordY;
    String nombre;
    String horario;

    /**
     * Constructor de la clase Local que inicializa las coordenadas, el nombre y
     * el horario del local.
     *
     * @param coordX La coordenada X de la ubicación del local.
     * @param coordY La coordenada Y de la ubicación del local.
     * @param nombre El nombre del local.
     * @param horario El horario de funcionamiento del local.
     */
    public Local(double coordX, double coordY, String nombre, String horario) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.nombre = nombre;
        this.horario = horario;
    }

    /**
     * Obtiene la coordenada X de la ubicación del local.
     *
     * @return La coordenada X.
     */
    public double getCoordX() {
        return coordX;
    }

    /**
     * Establece la coordenada X de la ubicación del local.
     *
     * @param coordX La nueva coordenada X a establecer.
     */
    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    /**
     * Obtiene la coordenada Y de la ubicación del local.
     *
     * @return La coordenada Y.
     */
    public double getCoordY() {
        return coordY;
    }

    /**
     * Establece la coordenada Y de la ubicación del local.
     *
     * @param coordY La nueva coordenada Y a establecer.
     */
    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    /**
     * Obtiene el nombre del local.
     *
     * @return El nombre del local.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del local.
     *
     * @param nombre El nuevo nombre a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el horario de funcionamiento del local.
     *
     * @return El horario de funcionamiento.
     */
    public String getHorario() {
        return horario;
    }

    /**
     * Establece el horario de funcionamiento del local.
     *
     * @param horario El nuevo horario a establecer.
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * Convierte el objeto Local en una representación de cadena de texto.
     *
     * @return Una cadena de texto que representa el objeto Local en el formato
     * "coordX,coordY,nombre,horario".
     */
    @Override
    public String toString() {
        return coordX + "," + coordY + "," + nombre + "," + horario;

    }

}
