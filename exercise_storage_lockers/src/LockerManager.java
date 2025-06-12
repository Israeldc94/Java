import java.util.ArrayList;

public class LockerManager {
    ArrayList<Locker> lockers = new ArrayList<>();

    IO io = new IO();

    public String addLocker(String lockerNumber) {
        lockers.add(new Locker(lockerNumber, "", false));
        return "Locker " + lockerNumber + " has been added.";
    }

    public String releaseLocker(String lockerNumber) {
        for (int i = 0; i <= lockers.size(); i++) {
            Locker locker = lockers.get(i);
            if (locker.getLockerNumber().equals(lockerNumber)) {
                lockers.remove(locker);
                return lockerNumber + " has been released!";
            }

        }
        return "Unable to locate locker";
    }

    public String getLocker(String lockerNumber) {
        for (int i = 0; i <= lockers.size(); i++) {
            Locker locker = lockers.get(i);
            if (locker.getLockerNumber().equals(lockerNumber)) {
                return lockerNumber;
            }
        }
        return lockerNumber + " not found.";
    }

    public String displayAllLockers() {
        for (int i = 0; i <= lockers.size(); i++) {
            Locker locker = lockers.get(i);
            return locker.toString(locker);
        }
        return "Fatal error";
    }
    //the act of renting the locker. sets is rented to true and assigns contents
    public String store(String lockerNumber, String contents) {
        for (int i = 0; i <= lockers.size(); i++) {
            Locker locker = lockers.get(i);
            if (locker.getLockerNumber().equals(lockerNumber)) {
                locker.setContents(contents);
                locker.setOccupied(true);
                return "Item stored in locker: " + lockerNumber;
            }
        }
        return lockerNumber + "not found";
    }

    //act of releasing the locker. Sets contents to null and isOccupied to false
    public String removeContents(String lockerNumber) {
            for (int i = 0; i <= lockers.size(); i++) {
                Locker locker = lockers.get(i);
                if (locker.getLockerNumber().equals(lockerNumber)) {
                    locker.setContents(null);
                    locker.setOccupied(false);
                    return "Item removed from locker: " + lockerNumber;
                }
            }
            return lockerNumber + "not found";
        }
    }
