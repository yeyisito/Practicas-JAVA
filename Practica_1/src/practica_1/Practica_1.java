/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_1;

import java.util.Scanner;

/**
 *
 * @author dieca
 */
public class Practica_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Enter number");
            arr[i] = in.nextInt();
            if (arr[i] < -10 || arr[i] > 10) {
                System.out.println("Value out of bounds (-10, 10)");
                i--;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        int month = 0;
        System.out.println("Enter Month's number");
        month = in.nextInt();
        switch (month) {
            case 2:
                System.out.print("February has 28 days");
                break;
            case 1, 3, 5, 9, 11:
                System.out.println("Month " + month + " has 30 Days");
                break;
            case 4, 6, 7, 8, 10, 12:
                System.out.println("Month " + month + " has 31 Days");
                break;
            default:
                System.out.println("Not a month");
        }
    }
}
