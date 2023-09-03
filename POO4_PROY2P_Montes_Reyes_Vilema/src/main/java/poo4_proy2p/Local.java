/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;

/**
 *
 * @author danie
 */
public class Local {
    private double coordX;
    private double coordY;
    String nombre;
    String horario;

    public Local(double coordX, double coordY, String nombre, String horario) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.nombre = nombre;
        this.horario = horario;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return coordX+","+coordY+","+nombre+","+horario;
                
    }
    
}
