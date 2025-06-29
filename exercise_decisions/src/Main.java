import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
// 1. Welcome the player and explain the scenario
// - Print an introduction message
// - Ask if they want to enter the cave
// - Get user input (yes/no)
        System.out.println("Welcome to the game. on your travels you come across a mysterious cave.");
        boolean gameOver = false;
        while (!gameOver) {
            System.out.println("Would you like to enter the cave? (y/n)");
            String enterCave = console.nextLine();

            // 2. If they enter, present two path choices (left or right)
            // - Use an if-else statement to process the decision
            // - If they go left, introduce an obstacle or creature
            // - If they go right, introduce a treasure room

            if (enterCave.equals("y")) {
                System.out.println("The inside of the cave is dark. You're barely able to make out a fork in the path");

                while (true) {
                    System.out.println("Will you go (l)eft or (r)ight? ");
                    String leftOrRight = console.nextLine();

                    if (leftOrRight.equals("l")) {
                        System.out.println("You encounter a Grue!");
                        while (true) {
                            System.out.println("Will you fight or flee?");
                            String fightOrFlight = console.nextLine();

                            if (fightOrFlight.equals("fight")) {
                                System.out.println("You manage to fend of the creature and escape the cave");
                                gameOver = true;
                                break;

                            } else if (fightOrFlight.equals("flee")) {
                                System.out.println("You were barely able to escape with your life.");
                                gameOver = true;
                                break;
                            } else {
                                System.out.println("Invalid choice");
                            }
                        }
                        break;

                    } else if (leftOrRight.equals("r")) {
                        System.out.println("You stumble upon a treasure room!");
                        System.out.println("You see a brightly colored gemstone, a gilded key, and a dusty book.");

                        while (true) {
                            System.out.println("Which do you choose? (gem, key, book)");
                            String choice = console.nextLine();
                            switch (choice) {
                                case "gem": {
                                    System.out.println("You retrieve the gem from the cave and lead a life of fortune and fame");
                                    gameOver = true;
                                    break;
                                }
                                case "key": {
                                    System.out.println("You embark on a quest to find which door the gilded key unlocks. TO BE CONTINUED");
                                    gameOver = true;
                                    break;
                                }
                                case "book": {
                                    System.out.println("You open the book and the knowledge of the universe flow into you. You ascend to a higher plane of existence a God");
                                    gameOver = true;
                                    break;
                                }
                                default: {
                                    System.out.println("Invalid input.");
                                    continue;
                                }

                            }
                            break;

                        }
                        if (gameOver) break;
                    } else {
                        System.out.println("Invalid input");

                    }

                }

                if (gameOver) break;
            } else if (enterCave.equals("n")) {
                System.out.println("You leave the cave and continue about your day");
                gameOver = true;
                break;
            } else {
                System.out.println("Invalid input");
            }

        }
        System.out.println("Thanks for playing!");
    }
}

