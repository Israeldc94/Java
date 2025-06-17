package org.example;

public class ExitSpell implements Spell{
    @Override
    public void cast() {
        System.out.println("A portal opens beneath your feet. Goodbye, wizard!");
        System.exit(0);
    }

    @Override
    public String getIncantation() {
        return "quit";
    }
}
