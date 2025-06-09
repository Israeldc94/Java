import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Random rand = new Random();

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
            if (input.equals(cities[i])) {
                found = true;
                break;
            }
        }
        if (found) ;
        System.out.println("City found!");

        int[] scores = {90, 85, 70, 95, 100};

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

        int average = sum / scores.length;

        System.out.println(average);

        int[] values = new int[10];
        for (int i = 0; i < values.length; i++) {
            values[i] = rand.nextInt(6) + 1;
        }
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == 3) {
                count++;
            }
        }
        System.out.println("3 appears " + count + " times.");

        int[] nums = {1, 2, 3, 4, 5};
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
        int first = nums[0];
        for (int i = 0; i < nums.length -1; i++) {
            nums[i] = nums[i+1];
        }
        nums[nums.length - 1] = first;
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
        String [] names = {"Jack", "Jill", "Jack", "Sam", "Smith","Luther"};
        boolean duplicate = false;
        for(int i =0; i < names.length; i++){
            for(int j = i +1; j < names.length; j++){
                if (names[i].equals(names[j])){
                    duplicate = true;
                    break;
                }
            }
            if(duplicate){
                break;
            }
        }
            if (duplicate){
                System.out.println("Duplicates found");
            }else{
                System.out.println("Duplicates not found");
            }
    }
}
