/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pck_camisa;

/**
 *
 * @author dieca
 */
public class Camisa {

    private int idCamisa;
    private String descripcion;
    private int talla;
    private String color;
    private float precio;

    public Camisa(int idCamisa, int talla, String descripcion, String color, float precio) {
        this.idCamisa = idCamisa;
        this.talla = talla;
        this.descripcion = descripcion;
        this.color = color;
        this.precio = precio;
    }

    public Camisa(String descripcion) {
        this(0, 0, descripcion, null, 0.0f);
    }

    public Camisa(int talla, String descripcion) {
        this(0, talla, descripcion, null, 0.0f);
    }

    public Camisa(int talla, String descripcion, String color) {
        this(0, talla, descripcion, color, 0.0f);
    }

    public Camisa() {
        this.idCamisa = 0;
        this.talla = 0;
        this.descripcion = null;
        this.color = null;
        this.precio = 0.0f;
    }

    public String getDatos() {
        return "ID: " + this.idCamisa + "\n"
                + "Talla: " + this.talla + "\n"
                + "Descripcion: " + this.descripcion + "\n"
                + "Color: " + this.color + "\n"
                + "Precio: " + this.precio + "\n";
    }
}
