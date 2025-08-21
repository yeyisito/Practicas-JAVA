/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pck_pet;

/**
 *
 * @author dieca
 */
public class Pet {

    private String name;
    private String type;
    private String breed;
    private String tutor;
    private int idPet;
    private float age;

    public Pet(String name, String type, String breed, String tutor, int idPet, float age) {
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.tutor = tutor;
        this.idPet = idPet;
        this.age = age;
    }

    public Pet() {
        this.name = null;
        this.type = null;
        this.breed = null;
        this.tutor = null;
        this.idPet = 0;
        this.age = 0.0f; // f to interpret the number as a a float, not a double   
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public void setId(int idPet) {
        this.idPet = idPet;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getBreed() {
        return breed;
    }

    public String getTutor() {
        return tutor;
    }

    public int getIdPet() {
        return idPet;
    }

    public float getAge() {
        return age;
    }

    public String getData() {
        return "ID: " + getIdPet() + "\n"
                + "Name: " + getName() + "\n"
                + "Type: " + getType() + "\n"
                + "Breed: " + getBreed() + "\n"
                + "Tutor: " + getTutor() + "\n"
                + "Age: " + getAge() + "\n";
    }

}
