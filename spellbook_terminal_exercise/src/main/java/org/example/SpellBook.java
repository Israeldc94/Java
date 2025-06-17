package org.example;

import java.util.ArrayList;

public class SpellBook {
    private ArrayList<Spell> Spellbook;

    public SpellBook() {
        Spellbook = new ArrayList<>();
        Spellbook.add(new Fireball());
        Spellbook.add(new Lightning());
        Spellbook.add(new Blizzard());
        Spellbook.add(new ExitSpell());


    }

    public void tryIncantation(String incantation) {
        boolean spellFound = false;

        for (int i = 0; i < Spellbook.size(); i++) {
            if (incantation.equals(Spellbook.get(i).getIncantation())) {
                Spellbook.get(i).cast();
                spellFound = true;
                break;
            }
        }
        if(!spellFound){
            System.out.println("The spell fizzled out! Try again.");
    }
}

}
