/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pck_camisa;

import javax.swing.JOptionPane;

/**
 *
 * @author dieca
 */
public class CamisaTest {

    public static void main(String args[]) {
        Camisa c1 = new Camisa(1, 12, "Camisa azul", "Azul", 15);
        Camisa c2 = new Camisa("Descripcion de camisa");
        Camisa c3 = new Camisa(25, "Camisa Azul");
        Camisa c4 = new Camisa(12, "Camisa Roja", "Rojo");
        Camisa c5 = new Camisa();
        JOptionPane.showMessageDialog(null, "Hola Merlina");
        System.out.println(c1.getDatos());
        System.out.println(c2.getDatos());
        System.out.println(c3.getDatos());
        System.out.println(c4.getDatos());
        System.out.println(c5.getDatos());
    }
}
