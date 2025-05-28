//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        double loanAmount = 25_000.00; // in dollars
        double annualInterestRate = 5.5; // as a percentage
        int loanTermYears = 5;
        double monthlyPayment;

        monthlyPayment = (loanAmount * (annualInterestRate/100)/12);

        loanAmount += 5_000;
        annualInterestRate --;
        loanTermYears ++;

        System.out.println(loanAmount > 30_000);
        System.out.println(monthlyPayment > 500);

        boolean affordable = (monthlyPayment < 500 && loanTermYears > 3);
        boolean expensive = (monthlyPayment > 700 || annualInterestRate > 6);

        System.out.print("Affordable: " + affordable +" Expensive: " + expensive);

        //Weather app

        double temperatureCelsius = 25.0; // Current temperature in Celsius
        boolean isRaining = false; // Indicates if it's raining
        int windSpeedKmh = 10; // Wind speed in km/h
        double temperatureFahrenheit = (temperatureCelsius * (9/5) + 32);
        temperatureFahrenheit += 5;
        windSpeedKmh += 5;

        System.out.println(temperatureFahrenheit > 85);
        System.out.println(windSpeedKmh > 20);

        boolean goodDay = (!isRaining && temperatureFahrenheit > 60 && temperatureFahrenheit < 85);

        boolean weatherWarning = (windSpeedKmh > 30 || temperatureCelsius < 0);

        System.out.println("Good day to go outside?: " + goodDay);
        System.out.println(" Weather Warning: " + weatherWarning);

// player score and level up system

        int currentXP = 1200; //experience points
        int level = 5;
        int xpToNextLevel = 1500;
        boolean levelUp;
        int xPGained = 300;
        boolean pro = level >= 7 || currentXP > 5000;


        //when completing a quest
        currentXP += xPGained;
        xpToNextLevel -= currentXP;

        //double XP boost
        currentXP *= xPGained;

        levelUp = currentXP >= xpToNextLevel && level < 10;

        System.out.println(" current XP: " + currentXP + " XP to next level: " + xpToNextLevel);
        System.out.println("Max level reached?: " + (level >= 10));
        System.out.println("Level: " + level);
        System.out.println("Level up?: " + levelUp);







        }
    }
