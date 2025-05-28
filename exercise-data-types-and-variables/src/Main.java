public class Main {
    public static void main(String[] args) {
        String playerName = "Christiano Ronaldo";
        int jerseyNumber = 7;
        String position = "Forward";
        boolean isStarter = true;
        String teamName = "Al-Nassr FC";

        String movieTitle = "Inception";
        int releaseYear = 2010;
        String rating = "PG-13";
        boolean isSequel = false;
        String leadActor = "Leonardo DeCaprio";

        String cityName = "Jarrell";
        String temperature = "85 ";
        boolean isRaining = false;
        String humidity = "56%";
        String weatherCondition = "partly cloudy skies";

        String flightNumber = "AA256";
        String departureCity = "New York";
        String arrivalCity = "Los Angeles";
        String gateNumber = "22";
        String terminal = "Terminal 2";
        boolean isDelayed = false;
        boolean hasHomework = true;
        boolean isWeekend = false;
        boolean attendedClass = true;
        char firstInitial = 'I';
        char lastInitial = 'C';
        char favoriteGrade = 'A';


        System.out.println("Soccer Player: " + playerName + " wears jersey number " +
            jerseyNumber + " and plays as a  " + position + " for " + teamName + "."   );
        System.out.println("The movie " + movieTitle + " was released in " + releaseYear + " and stars "
        + leadActor + ".");
        System.out.println("Weather Report: " + cityName + " has a temperature of " + temperature + "°F with " +
         weatherCondition + ".");

        System.out.println("Do I have homework? " + hasHomework);
        System.out.println("Is it the weekend? " + isWeekend);
        System.out.println("Did I attend class today? " + attendedClass);

        System.out.println("My first initial is " + firstInitial);
        System.out.println("My last initial is " + lastInitial);
        System.out.println("My favorite grade is " + favoriteGrade);

        jerseyNumber = 8;
        isStarter = false;
        rating = "PG";
        isSequel = true;
        temperature = "95";
        weatherCondition = "Sunny skies";
        gateNumber = "33";
        isDelayed = true;

        System.out.println("Soccer Player: " + playerName + " wears jersey number " +
                jerseyNumber + " and plays as a  " + position + " for " + teamName + "."   );
        System.out.println("The movie " + movieTitle + " was released in " + releaseYear + " and stars "
                + leadActor + ".");
        System.out.println("Weather Report: " + cityName + " has a temperature of " + temperature + "°F with " +
                weatherCondition + ".");




    }
}
