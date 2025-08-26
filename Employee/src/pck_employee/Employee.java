package pck_employee;

public class Employee {

    private int noEmployee;
    private String name;
    private String dpt;
    private int year;
    private double[] sales = new double[12];

    public Employee(int noEmployee, String name, String dpt, int year) {
        this.noEmployee = noEmployee;
        this.name = name;
        this.dpt = dpt;
        this.year = year;
        for (int i = 0; i < 12; i++) {
            this.sales[i] = 0.0;
        }
    }

    public Employee() {
        this(0, null, null, 0);
    }

    public int getNoEmployee() {
        return noEmployee;
    }

    public void setNoEmployee(int noEmployee) {
        this.noEmployee = noEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getSale(int month) {
        return sales[month - 1];
    }

    public double getAnualSales() {
        double total = 0.0;
        for (int i = 0; i < 12; i++) {
            total += sales[i];
        }
        return total;
    }

    public void setSales(int month, double sale) {
        sales[month - 1] = sale;
    }

    public String getData() {
        String HEADER = "Month | Sales\n-------------\n";
        for (int i = 1; i < 13; i++) {
            HEADER += i + " | " + getSale(i) + '\n';
        }
        return "Employee data: \n"
                + "Number of Employee: " + getNoEmployee() + "\n"
                + "Name: " + getName() + "\n"
                + "Department: " + getDpt() + "\n"
                + "Year: " + getYear() + "\n"
                + HEADER;

    }
}
