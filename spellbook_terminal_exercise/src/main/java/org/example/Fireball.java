package org.example;

public class Fireball implements Spell{

    @Override
    public void cast() {
        System.out.println("Fire spews forth from your fingertips at your target.");
    }

    @Override
    public String getIncantation() {
        return "Brisingr";
    }
}
