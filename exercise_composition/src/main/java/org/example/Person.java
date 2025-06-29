package org.example;

public class Person {
    private String firstName;
    private String lastName;
    private String primarySpecialty;

    public Person(String firstName, String lastName, String primarySpecialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.primarySpecialty = primarySpecialty;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrimarySpecialty() {
        return primarySpecialty;
    }

    public void setPrimarySpecialty(String primarySpecialty) {
        this.primarySpecialty = primarySpecialty;
    }
}
