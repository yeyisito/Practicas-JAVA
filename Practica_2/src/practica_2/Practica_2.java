/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_2;

import java.util.Scanner;

/**
 *
 * @author dieca
 */
public class Practica_2 {
     
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] grades = new int[999];
        int c = 0;
        System.out.println("Please enter valid grades: ");
        while (grades[c] != 99) {
            c++;
            grades[c] = in.nextInt();
            if ((grades[c] < 0 || grades[c] > 10) && grades[c] != 99) {
                System.out.println("Please enter a valid grade...");
                c--;
            }
            if (grades[c] == 99) {
                System.out.println("Breaking grades inputting");
                c--;
                break;
            }
        }
        double sum = 0;
        double mean;
        System.out.println("Number of inputted grades: " + c);
        for (int i = 1; i <= c; i++) {
            System.out.println("Grade " + i + ": " + grades[i]);
            sum += grades[i];
        }

        mean = sum / c;
        System.out.println("Mean: " + mean);
    }

}
