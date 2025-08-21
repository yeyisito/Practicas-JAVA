/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pck_punto;

import java.util.Scanner;

/**
 *
 * @author dieca
 */
public class PuntoTest {

    public static void main(String[] args) {

        Punto p1 = new Punto(1, 2);
        Punto p2 = new Punto(2, 1);
        Punto p3 = new Punto();
        Scanner in = new Scanner(System.in);
//        p1.setX(1);
//        p1.setY(2);
//        p2.setX(13);
//        p2.setY(24);
        System.out.println("P1: ");
        System.out.println(p1.getX() + "," + p1.getY());
        System.out.println("P2: ");
        System.out.println(p2.getX() + "," + p2.getY());
        System.out.println("P3: ");
        System.out.println(p3.getX() + "," + p3.getY());
        System.out.println("Enter new P1 coordinates: ");
        System.out.print("x: ");
        System.out.println();
        p1.setX(in.nextInt());
        System.out.print("y: ");
        System.out.println();
        p1.setY(in.nextInt());
        System.out.println("P1: ");
        System.out.println(p1.getX() + "," + p1.getY());

    }

}
