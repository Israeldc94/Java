package org.example;

public class Blizzard implements Spell{
    @Override
    public void cast() {
        System.out.println("Your target freezes over, encased in a thin layer of ice.");
    }

    @Override
    public String getIncantation() {
        return "Svell";
    }
}
