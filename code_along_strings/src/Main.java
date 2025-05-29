import java.sql.SQLOutput;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
String example = "This is a String literal being assigned to a variable.";

String firstHalf = "Jack and Jill ";
String secondHalf = "went up the hill";
String fullSentence = firstHalf + secondHalf;

        System.out.println(fullSentence);
        System.out.println("Count the characters".length());
        String countThese = "Count the characters";
        System.out.println(countThese.length()); // Prints the number 20

        String searchThis = "The quick brown fox jumped over the lazy dog";

        System.out.println(searchThis);

        System.out.println("Looking for the letter 'e'...");

        System.out.println(searchThis.indexOf('e'));

        String searchThis2 = "Looking for: quick";

        System.out.println("Looking for: quick");
        System.out.println(searchThis2.indexOf("quick"));
        System.out.println("I won't find the word: foobar");
        System.out.println(searchThis.indexOf("foobar"));

        String dateTime = "20250529 04:39:00";
        String year = dateTime.substring(0, 4);

        String month = dateTime.substring(4, 6);

        String day = dateTime.substring(6, 8);

        String hours = dateTime.substring(9, 11);

        String minutes = dateTime.substring(12, 14);

        String seconds = dateTime.substring(15, 17);

        System.out.println("Year: " + year);
        System.out.println("Month: " + month);
        System.out.println("Day: " + day);
        System.out.println("Hours: " + hours);
        System.out.println("Minutes: " + minutes);
        System.out.println("Seconds: " + seconds);

        String[] dateAndTime = dateTime.split(" ");

        String[] timeFields = dateAndTime[1].split(":");

        System.out.println("Hours: " + timeFields[0]);

        System.out.println("Minutes: " + timeFields[1]);

        System.out.println("Seconds: " +timeFields[2]);

        final int DATE_FIELD = 0;

        final int   TIME_FIELD = 1;

        final int HOURS = 0;

        final int MINUTES = 1;

        final int SECONDS = 2;

        System.out.println("Hours: " +timeFields[HOURS]);

        System.out.println("Minutes: " + timeFields[MINUTES]);

        System.out.println("Seconds: " + timeFields[SECONDS]);

        String data = "001SOMETHINGY12345";

        System.out.println("Do I process this line? " + data.charAt(12));

        String underscoredVersion = "This_text_needs_spaces_in_it";

        System.out.println(underscoredVersion);

        System.out.println(underscoredVersion.replace('_',' ').replace("needs", "has"));






    }
}