//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    String firstName = "Harry";
    String lastName = "Potter";
    String fullName = firstName + " " + lastName;

        System.out.println("Full name: " + fullName);
        System.out.println("Length: " + fullName.length());
        System.out.println("First character: " + fullName.charAt(0));
        System.out.println("Index of 'r': " + fullName.indexOf('r'));

        String sentence = "Learning Java is fun! ";

        System.out.println("First word: " + sentence.substring(0, 8));
        System.out.println("Second word: " + sentence.substring(9, 13));
        System.out.println("Last word: " + sentence.substring(17));

        String csvData = "apple,banana,grape,orange";
        String[] data = csvData.split(",");
        for (int i = 0; i < data.length; i++) {
            System.out.println("Fruit" + (i+1) + ": " + data[i]);


            String sentence1 = "The quick brown fox.";

            System.out.println(sentence1.replace(' ', '_').replace("quick", "slow"));

            String emptyString = null;
            boolean isNull = (emptyString ==  null);

            if (isNull){
                System.out.println("The string is null, cannot compute length");
            }
            String emptyString1 = "";
            System.out.println("Empty string length: " + emptyString1.length());




        }

    }
}