
public class Main {
    public static void main(String[] args) {
    IO io = new IO();
    LockerManager service = new LockerManager();
    io.print(service.addLocker());
    }
}