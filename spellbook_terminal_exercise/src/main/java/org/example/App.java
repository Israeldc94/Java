package org.example;
import java.util.Scanner;
public class App

{
    public static void main( String[] args )
    {
        Scanner io = new Scanner(System.in);
        SpellBook spellbook =  new SpellBook();

        while (true){
            System.out.println("Recite a spell: ");
            String incantation = io.nextLine();
            spellbook.tryIncantation(incantation);

        }
    }
}
