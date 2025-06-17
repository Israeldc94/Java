package org.example;

public class Lightning implements Spell{
    @Override
    public void cast() {
        System.out.println("Lightning strikes the object of your focus.");

    }

    @Override
    public String getIncantation() {
        return "Kveykva";
    }
}
