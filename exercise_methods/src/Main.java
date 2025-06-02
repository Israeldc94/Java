import java.util.Scanner;

public class Main {
    public static void printWelcomeMessage() {
        System.out.println("Welcome to the Java Methods Exercise!");
    }

    public static int sum(int a, int b) {
        int sum = a + b;
        System.out.println(sum);
        return sum;
    }

    public static double convertToFahrenheit(double celsius) {
        double Fahrenheit = (celsius * (9 / 5) + 32);
        System.out.println(Fahrenheit);
        return Fahrenheit;
    }

    public static boolean isEven(int number) {
        boolean even;
        if (number % 2 == 0) {
            even = true;
        } else {
            even = false;
            return even;
        }
        System.out.println(even);
        return even;
    }

    public static void printMultipleTimes(String text, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(text);
        }

    }

    public static int findMax(int a, int b, int c) {
        int max = Math.max(a, b);
        return Math.max(c, max);
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
    public static String greet(String name){
        String message = " Hello " + name;
        return message;
    }
    public static String greet(String name, int age){
        String message = "Hello, " + name + " You are " + age + " years old.";
        return message;
    }
    public static int countVowels(String text) {
        int count = 0;
        for (int i = 0; i <= text.length() - 1 ; i++) {
            if (text.charAt(i) == 'a' || text.charAt(i) == 'e' || text.charAt(i) == 'i' ||
                    text.charAt(i) == 'o' || text.charAt(i) == 'u') {
                count++;
            }
        }
        return count;
    }
    public static int calculator(int num1, int num2, char operator){
        switch (operator){
            case '+':
                int sum = num1 + num2;
                return sum;
            case '-':
                int difference = num1 - num2;
                return difference;
            case '*':
                int product = num1 * num2;
                return product;
            case '/':
                int quotient = num1 / num2;
                return quotient;
            default:
                System.out.println("Not a valid operator");

        }
        return 0;

    }


    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        printWelcomeMessage();
        sum(1, 2);
        convertToFahrenheit(80);
        isEven(2);
        printMultipleTimes("Fubar", 3);
        System.out.println(findMax(1, 2, 3));
        //factorial(5);
        System.out.println(factorial(5));
        System.out.println(greet("Izzy"));
        System.out.println(greet("Izzy", 25));
        System.out.println(countVowels("oahu"));
        System.out.println(calculator(2,2,'+'));

    }
}

