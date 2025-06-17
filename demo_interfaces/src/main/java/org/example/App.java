package org.example;

public class App
{
    public static void main( String[] args )
    {
        Pond myPond = new Pond("Front Pond", 27.0, "Catfish",
                true);
        Aquarium myAquarium = new Aquarium("100 Gallon Tank",
                23.0, "Oscar", "Floating Water Moss");
        Terrarium myTerrarium = new Terrarium("Little Turtle
                Tank", 27.0, 22.0, "Ornate Box Turtle");
                System.out.println("Welcome to the Aquarium Manager!");
        System.out.println("Here are the current exhibits: ");
        System.out.println("The " + myPond.getName() +
                " houses the " + myPond.getFish());
        System.out.println("The " + myAquarium.getName() +
                " houses the " +
                myAquarium.getFish() + " and " +
                myAquarium.getPlant());
        System.out.println("The " + myTerrarium.getName() +
                " houses the " +
                myTerrarium.getTurtle());
    }
}

    }
}
