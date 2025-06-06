import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        String[] cities = {"San antonio", "San marcos", "Austin", "New york", "San diego"};

        for (int i = 0; i < cities.length; i++) {
            System.out.println(cities[i]);
        }
        cities[2] = "Los Angeles";

        for (int i = 0; i < cities.length; i++) {
            System.out.println(cities[i]);
        }

        System.out.println(cities.length);

        for (int i = cities.length - 1; i >= 0; i--) {
            System.out.println(cities[i]);
        }

        boolean found = false;
        System.out.println("Enter a city");
        String input = console.nextLine();
        for (int i = 0; i < cities.length; i++) {
            if (input.equals(cities[i])){
                found = true;
                break;
            }
        }
        if (found);
        System.out.println("City found!");

        int [] scores = {90, 85, 70, 95, 100};

        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
        }
            System.out.println(sum);

        int highest = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > highest) {
                highest = scores[i];
            }
        }
        System.out.println(highest);

        int lowest = 105;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] < lowest) {
                lowest = scores[i];
            }
        }
        System.out.println(lowest);

        int average = sum/scores.length;

        System.out.println(average);
    }
}

