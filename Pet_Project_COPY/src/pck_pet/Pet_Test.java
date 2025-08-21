/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pck_pet;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author dieca
 */
public class Pet_Test {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Pet pet1 = new Pet();
        Pet pet2 = new Pet("Ramiro", "Perro", "Chihuahua", "Maiky", 1, 10);
        int id = 0;
        do {
            try {
                id = Integer.parseInt(JOptionPane.showInputDialog("Enter pet's id"));
                if (id <= 0) {
                    JOptionPane.showMessageDialog(null, "ID MUST BE> 0");

                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID MUST BE NUMERIC error: " + e, "INPUT EXCEPTION", JOptionPane.ERROR_MESSAGE);
            }
        } while (id <= 0);

        pet1.setId(id);

        pet1.setName(JOptionPane.showInputDialog("Enter pet's name"));

        pet1.setType(JOptionPane.showInputDialog("Enter " + pet1.getName() + "'s type"));

        pet1.setBreed("Enter " + pet1.getName() + "'s breed");
        float age = -1.0f;
        do {
            try {
                age = Float.parseFloat(JOptionPane.showInputDialog("Enter" + pet1.getName() + "s' age"));
                if (age < 0) {
                    JOptionPane.showMessageDialog(null, "ID MUST BE> 0");

                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "AGE MUST BE NUMERIC error: " + e, " INPUT EXCEPTION", JOptionPane.ERROR_MESSAGE);
            }
        } while (age <= -1);
        pet1.setAge(age);

        pet1.setTutor(JOptionPane.showInputDialog("Enter tutor's name"));

        JOptionPane.showMessageDialog(null, pet1.getData());
        JOptionPane.showMessageDialog(null, pet2.getData());
    }

}
