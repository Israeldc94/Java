import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean keepGoing = true;
        boolean keyHalf1 = false;
        boolean keyHalf2 = false;
        System.out.println("Welcome to the game!");
        System.out.println("What is your name? ");
        String name = console.nextLine();
        System.out.println("Hello " + name);
        String mainMenu = ("Your journey begins in a room with three doors. Which one do you enter?" +
                "\n1. the rotting wooden door" +
                "\n2. the rusted metal door" +
                "\n3. the gilded double doors" +
                "Enter q to quit");
        String room1 = "You find yourself in a rancid swamp. not a soul in sight. deep within the murk a glimmer catches your eye. " +
                "You reach for it and find half a key";
        String room1Repeat = "You find yourself in a rancid swamp. You have been here before.";
        String room2 = "You find yourself in a smokey factory from the industrial era, standing in front of a forge. " +
                "Inside the forge you see a glimmer." + "\nWhen you reach for the shine you are surprised to see your hand doesn't burn." +
                "You open your hand to reveal that the glimmer was a key.";
        String room2Repeat = "You find yourself in a smokey factory. You've been here before.";

        String room3Locked = "You struggle against the gilded double doors but they won't budge. You spy a key hole waiting for its partner.";
        String room3Unlocked = " You insert the key and hear an audible 'click' The key vanishes into particles of blue light. You push open the double doors. " +
                "inside you witness the secret of everything...";

        while (keepGoing) {
            System.out.println(mainMenu);
            String door = console.nextLine();
            switch (door) {
                case "1":
                    if (keyHalf1) {
                        System.out.println(room1Repeat);
                        break;
                    } else {
                        System.out.println(room1);
                        keyHalf1 = true;
                        break;
                    }
                case "2":
                    if (keyHalf2) {
                        System.out.println(room2Repeat);
                        break;
                    } else {
                        System.out.println(room2);
                        keyHalf2 = true;
                        break;
                    }
                case "3":
                    if (keyHalf1 && keyHalf2) {
                        System.out.println(room3Unlocked);
                        keepGoing = false;
                        break;
                    } else {
                        System.out.println(room3Locked);
                        break;
                    }
                case "Q":
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        }
        System.out.println("Thanks for playing, " + name + "!");
    }
}
