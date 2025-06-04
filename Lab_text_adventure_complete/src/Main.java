import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean keepGoing = true;

        String[] rooms = new String[5];
        String[] visitedRooms = new String[5];
        boolean[] keys = new boolean[4];
        keys[0] = false;
        keys[1] = false;
        keys[2] = false;
        keys[3] = false;

        String mainMenu = ("You're in a room with Five doors. Which one do you enter?" +
                "\n1. the rotting wooden door" +
                "\n2. the rusted metal door" +
                "\n3. the gilded double doors" +
                "\n4. the glistening silver door" +
                "\n5. the shining gold door" +
                "\n Enter Q to quit");

        rooms[0] = "You find yourself in a rancid swamp. not a soul in sight. deep within the murk a glimmer catches your eye. " +
                "You reach for it and find half a key";
        visitedRooms[0] = "You find yourself in a rancid swamp. You have been here before.";
        rooms[1] = "You find yourself in a smokey factory from the industrial era, standing in front of a forge. " +
                "Inside the forge you see a glimmer." + "\nWhen you reach for the shine you are surprised to see your hand doesn't burn. " +
                "You open your hand to reveal that the glimmer was a key.";
        visitedRooms[1] = "You find yourself in a smokey factory. You've been here before.";

        rooms[2] = "You struggle against the gilded double doors but they won't budge. You spy a key hole waiting for its partner.";
        visitedRooms[2] = " You insert the key and hear an audible 'click' The key vanishes into particles of blue light. You push open the double doors. " +
                "inside you witness the secret of everything...";
        rooms[3] = " You step into an icy plain. The snow is almost blinding to your eyes. Squinting you see a tiny seal with a key portion in its mouth." +
                "It hands you the key and says 'Sliiiiiiiide' and slips away. The key portion in your hand glistens like the snow around you.";
        visitedRooms[3] = "You step into an icy plain. No seal to be found. You've been here before.";
        rooms[4] = "You step through the door and to your surprise, you find yourself at home. You wonder just how long you've been away." +
                "You glance at a table in your living room and find the envelope you received. This was to contain the details of your inheritance." +
                "You open the envelope to find a key portion. the self same one you brought with you to this mysterious place. How could you forget?";
        visitedRooms[4] = "You step through the door to find yourself at home. You've found the key portion here already. No time to rest";

        //application loop
        print("Welcome to the game!");
        String name = promptString("What is your name? ");
        print("Hello " + name);
        while (keepGoing) {
            String door = promptString(mainMenu);
            keepGoing = doorLogic(keys, rooms, visitedRooms, door);

        }
        print("Thanks for playing, " + name + "!");
    }
    public static void print(String message) {
        System.out.println(message);
    }
    public static String promptString(String message) {
        Scanner console = new Scanner(System.in);
        print(message);
        return console.nextLine();
    }
    public static boolean doorLogic(boolean [] keys, String [] rooms, String [] visitedRooms, String door){
        try {
            boolean keepGoing = true;
            switch (door) {
                case "1":
                    if (keys[0]) {
                        print(visitedRooms[0]);
                        break;
                    } else {
                        print(rooms[0]);
                        keys[0] = true;
                        break;
                    }

                case "2":
                    if (keys[1]) {
                        print(visitedRooms[1]);
                        break;
                    } else {
                        print(rooms[1]);
                        keys[1] = true;
                        break;
                    }

                case "3":
                    boolean allKeys = true;
                    for (int i = 0; i < keys.length; i++) {
                        if (!keys[i]) {
                            allKeys = false;
                            break;

                        }
                    }

                    if (allKeys){
                        print(visitedRooms[2]);
                        keepGoing = false;
                        break;
                    } else {
                        print(rooms[2]);
                    }
                    break;

                case "4":
                    if (keys[2]) {
                        print(visitedRooms[3]);
                        break;
                    } else {
                        print(rooms[3]);
                        keys[2] = true;
                        break;
                    }

                case "5":
                    if (keys[3]) {
                        print(visitedRooms[4]);
                        break;
                    } else {
                        print(rooms[4]);
                        keys[3] = true;
                        break;

                    }

                case "Q":
                    keepGoing = false;
                    break;

                default:
                    print("Invalid choice");
            }

            return keepGoing;
        } catch (NumberFormatException e) {
            print("Invalid Menu option. Please try again");
            throw new RuntimeException(e);
        }

    }

}
