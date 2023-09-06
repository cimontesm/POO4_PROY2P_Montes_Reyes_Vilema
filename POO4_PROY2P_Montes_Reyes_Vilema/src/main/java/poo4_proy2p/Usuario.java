/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo4_proy2p;
/**
 * Clase Usuario nos ayuda a crear instancias y a manipularlas a lo largo del camino.
 *
 * @author Cecilia Montes
 * @author Kimberly Reyes
 * @author Daniel Vilema
 */
public class Usuario {
    String usuario;
    String contrasenia;
/**
 * Crea un nuevo objeto Usuario con el nombre de usuario y la contraseña especificados.
 *
 * @param usuario     El nombre de usuario del nuevo usuario.
 * @param contrasenia La contraseña del nuevo usuario.
 */
    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }
/**
 * Obtiene el nombre de usuario del usuario.
 *
 * @return El nombre de usuario del usuario.
 */
    public String getUsuario() {
        return usuario;
    }
/**
 * Establece el nombre de usuario del usuario.
 *
 * @param usuario El nuevo nombre de usuario a establecer.
 */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
/**
 * Obtiene la contraseña del usuario.
 *
 * @return La contraseña del usuario.
 */
    public String getContrasenia() {
        return contrasenia;
    }
/**
 * Establece la contraseña del usuario.
 *
 * @param contrasenia La nueva contraseña a establecer.
 */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    //por si acaso, pq no se si lo necesitemos
    @Override
    public String toString() {
        return "Usuario{" + "usuario=" + usuario + ", contrasenia=" + contrasenia + '}';
    }
    
    
    
}
