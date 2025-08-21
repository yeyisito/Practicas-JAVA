/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesDeUtilidad;

/**
 *
 * @author dieca
 */
//Clase arista y variales de clase
public class Arista {

    //Variable de clase de tipo Clase Nodo (con todo lo que almacena, nombre, posicion en X y en Y)
    private Nodo origen;
    private Nodo destino;
    //Peso, para la ponderación
    private int peso;
    //¿Es dirigido?
    private boolean dirigido;
    
    //Constructor de clase
    public Arista(Nodo origen, Nodo destino, int peso, boolean dirigido) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.dirigido = dirigido;
    }
    
    //--------------------METODOS DE CLASE-----------------------------//
    public Nodo getOrigen(){
        return origen;
    }
    
    public Nodo getDestino(){
        return destino;
    }
    
    public int getPeso(){
        return peso;
    }
    
    public boolean esDirigido(){
        return dirigido;
    }
    
    //Sobrecargamos metodo toString para esta clase, este metodo regresa
    //la cadena completa del origen, su peso y su destino.
    @Override
    public String toString(){
        return origen + " -(" + peso + ") " + destino;
    }
    
    
    
}
