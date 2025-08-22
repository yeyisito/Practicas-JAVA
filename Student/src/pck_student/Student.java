/*
 */
package pck_student;

/**
 *
 * @author dieca
 */
public class Student {

    private int accNum;
    private String name;
    private int semester;
    private int group;
    private String career;
    private float ovrAvr;

    public Student(int accNum, String name, int semester, int group, String career, float ovrAvr) {
        this.accNum = accNum;
        this.name = name;
        this.semester = semester;
        this.group = group;
        this.career = career;
        this.ovrAvr = ovrAvr;
    }

    public Student() {
        this.accNum = 0;
        this.name = null;
        this.semester = 0;
        this.group = 0;
        this.career = null;
        this.ovrAvr = 0.0f; 
    }

    public int getAccNum() {
        return accNum;
    }

    public void setAccNum(int accNum) {
        this.accNum = accNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public float getOvrAvr() {
        return ovrAvr;
    }

    public void setOvrAvr(float ovrAvr) {
        this.ovrAvr = ovrAvr;
    }

    public String getData() {
        return "Mr." + getName() + "\n"
                + "With account number: " + getAccNum() + "\n"
                + "Currently in " + getSemester() + "st/nd/d/th year \n"
                + "in group " + getGroup() + "\n"
                + "Studying the carrer of" + getCareer() + "\n"
                + "Having " + getOvrAvr() + " of average\n";
    }

}