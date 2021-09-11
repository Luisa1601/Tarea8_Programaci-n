/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author Ruldin
 */
public class AccesoDatosEx extends Exception {

    String Mensaje;

    public AccesoDatosEx(String msg) {
        this.Mensaje=msg;
    }
}
