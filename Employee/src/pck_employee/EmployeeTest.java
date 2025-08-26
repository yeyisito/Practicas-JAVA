package pck_employee;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class EmployeeTest {

    public static void main(String[] args) {
        Employee emp1 = new Employee();
        int eN = 0, op, year = 0;
        String month;
        LocalDate date = LocalDate.now();
        HashMap<Integer, String> months = new HashMap<>();
        Object[] ops = months.keySet().toArray();
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8,"August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");

        final String MENU = "--- Employee Sales ---\n"
                + "1) New Data \n"
                + "2) New Sale \n"
                + "3) Get Data of a single sale \n"
                + "4) Get Data of every sale\n"
                + "5) Exit\n"
                + "Please choose...";

        do {
            do {
                try {
                    op = Integer.parseInt(JOptionPane.showInputDialog(MENU));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Enter a valid option", "Error: " + e, JOptionPane.WARNING_MESSAGE);
                    op = 0;
                }
            } while (op == 0);

            switch (op) {
                case 1:
                    emp1.setName(JOptionPane.showInputDialog("Please enter employee's name: "));
                    do {
                        try {
                            eN = Integer.parseInt(JOptionPane.showInputDialog("Please enter employee's number"));
                            if (eN <= 0) {
                                JOptionPane.showMessageDialog(null, "Input must be greater than 0", "Error", JOptionPane.WARNING_MESSAGE);

                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Input must be numeric", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (eN <= 0);
                    emp1.setNoEmployee(eN);
                    emp1.setDpt(JOptionPane.showInputDialog("Please enter employee's department: "));
                    do {
                        try {
                            year = Integer.parseInt(JOptionPane.showInputDialog("Please enter year of record"));

                            if (year <= 2000 || year > date.getYear()) {
                                JOptionPane.showMessageDialog(null, "No valid records before 2000, or after current year", "Error", JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Input must be numeric (YYYY)", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (year <= 2000 || year > date.getYear());
                    break;
                case 2:

                    month = JOptionPane.showInputDialog(null, "Please type a month to set sale", "Month selector");

                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    op = 5;
                    break;
                default:
            }

        } while (op == 5);

    }

}
