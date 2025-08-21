/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package p_numeros;

import java.util.Scanner;

/**
 *
 * @author dieca
 */
public class P_Numeros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int a[] = new int[5];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < a.length; i++) {
            a[i] = in.nextInt();
        }
        System.out.println("Enter a value to search");
        int num = in.nextInt();
        //WITHOUT FUNCTIONS
        boolean flag = false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == num) {
                System.out.println("Found at position: " + i);
                flag = true;
                break;
            }
        }

        if (!flag) {
            System.out.println("Value not found");
        }
        
        /**
         * *************************************************
         */

        //WITH FUNCTIONS
        
        if (seek(num, a) != -1) {
            System.out.println("Value found at: " + seek(num, a));
        } else {
            System.out.println("Value not found");

        }
    }

    public static int seek(int n, int[] a) {
        boolean found = false;
        int pos = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == n) {
                return i;
            }
        }
        return pos;
    }
}
