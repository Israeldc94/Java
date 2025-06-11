import java.util.ArrayList;

public class LockerManager {
ArrayList<Locker> lockers = new ArrayList<>();

IO io = new IO();

public String addLocker(){
  String lockerNumber = io.getNonEmptyString("Enter a Locker ID: ");
  lockers.add(new Locker(lockerNumber, "", false));
    return "Locker " + lockerNumber + " has been added.";
}


}

