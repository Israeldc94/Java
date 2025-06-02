import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Random random = new Random();

        for (int i = 0; i <= 100; i += 2) {
            System.out.println(i);
        }

        System.out.println("Enter a starting number: ");
        String input = console.nextLine();
        int countdownStart = Integer.parseInt(input);
        while (countdownStart > 0) {
            System.out.println(countdownStart);
            countdownStart--;
            if (countdownStart == 0) {
                System.out.println("Blast off!");
            }
        }
        int number = random.nextInt(50) + 1;
        System.out.println("Debug " + number); //remove after debugging
        input = "0";
        int guess;
        System.out.println("Guess a number between 1 and 50: ");
        do {
            System.out.println("input your guess");
            input = console.nextLine();
            guess = Integer.parseInt(input);
        } while (guess != number);

        System.out.println("You got it! The number was " + number);

        //intermediate challenges
        //mutliplication table
        input = " ";
        System.out.println("Enter a number: ");
        input = console.nextLine();
        int nbr = Integer.parseInt(input);
        for (int i = 0; i <= 10; i++) {
            System.out.println(nbr * i);
        }
        //password validator
        input = " ";
        while(!input.equals("letmein")){
            System.out.println("Enter a password: ");
            input = console.nextLine();
        }
        //first vowel search
        input = " ";
        System.out.println("Enter a word: ");
        input = console.nextLine();
        for (int i=0; i<=input.length(); i++){
            char c = input.charAt(i);

            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'y') {
                System.out.println(c +" " + i);
                break;
            }
        }
        //simple ATM system
        int account_balance = 500;
        input = " ";
        do{
            System.out.println("1. Withdraw\n 2. Deposit\n 3.Check Balance\n 4.Exit");
            input = console.nextLine();
        }while (!input.equals("4"));

        //Advanced Loops exercises
        //FizzBuzz
        for(int i =1; i <=100; i++){
            if(i % 3 == 0 && i % 5 == 0){
                System.out.println("FizzBuzz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            }else{
                System.out.println(i);
            }
        }
        //Reversing a string
        input = " ";
        System.out.println("Enter a word");
        input = console.nextLine();
        for(int i = input.length()-1; i >= 0; i-- ){
            System.out.print(input.charAt(i));
        }
        //prime number checker
        boolean isPrime = true;
        System.out.println("\nEnter a number: ");
        input = console.nextLine();
        number = Integer.parseInt(input);

        if (number <= 1){
            isPrime = false;

        }else{
            int i = 2;
            while(i * i <= number) {
                if (number % i == 0){
                    isPrime = false;
                    break;
                }
                i++;
            }
        }
        if (isPrime){
            System.out.println(number + " is prime!");
        }else{
            System.out.println(number + " is not prime.");
        }
        //word counts
        System.out.println("Enter a sentence: ");
        input = console.nextLine();
        String[] words = input.split(" ");
        int count= 0;
        for (int i = 0; i < words.length; i++){
            count ++;
        }
        System.out.println(count);

        //continue challenge
        while (true){
            System.out.println("Enter a number (0 to quit)");
            input = console.nextLine();
            number = Integer.parseInt(input);
            if(number < 0){
                System.out.println(" Negative numbers aren't allowed.");
                continue;
            }else if(number == 0){
                break;
            }else{
                System.out.println(number);
            }
        }
        //find the first prime number in a range
        System.out.println("Enter a starting number: ");
        input = console.nextLine();
        int startNum = Integer.parseInt(input);

        System.out.println("Enter an ending number: ");
        String input2 = console.nextLine();
        int endNum = Integer.parseInt(input2);


        for(int i = startNum; i <= endNum; i++){
            boolean prime = true;
            if (i <= 1) {
                prime = false;
            }else{
                int z = 2;
                while(z * z <= i){
                    if(i % z == 0){
                        prime = false;
                        break;
                    }
                    z++;
                }
            }
            if (prime){
                System.out.println("First prime found in range: " + i);
                break;
            }
            if (!prime){
                System.out.println("no prime numbers found in that range");
            }
        }
        int i = 0;
        while(i<10){
            System.out.println(i);
            i++;
        }
    }
}








