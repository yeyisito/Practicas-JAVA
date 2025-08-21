/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesDeUtilidad;

/**
 *
 * @author dieca
 */
//Clase Nodo y variables de clase
public class Nodo {

    private String nombre;
    private int posX;
    private int posY;

    //Constructor de clase
    public Nodo(String nombre, int posX, int posY) {
        this.nombre = nombre;
        this.posX = posX;
        this.posY = posY;
    }

    //--------------------METODOS DE CLASE-----------------------------//
    public String getNombre() {
        return nombre;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    //Sobreecargamos metodo toString para esta clase, esto nos será útil 
    //posteriormente a la hora de establecer nombres personalizados a los nodos
    @Override
    public String toString() {
        return nombre;
    }
}
