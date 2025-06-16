package org.example;

public class Enclosure {
    private String name;
    private Inhabitant resident;
    private double temp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inhabitant getResident() {
        return resident;
    }

    public void setResident(Inhabitant resident) {
        this.resident = resident;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
