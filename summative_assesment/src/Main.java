
public class Main {
    public static void main(String[] args) {
        IO io = new IO();

        int num = io.getInt("Enter a number between 1 & 10 ", 1, 10);
        io.print("hello");
        io.getNonEmptyString("Enter a string");
        io.getValidPin("Enter your pin");
    }
    
}