//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    enum CoffeeSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    enum SeatSection {
        GENERAL,
        PREMIUM,
        VIP
    }

    enum TrafficLight {
        RED,
        YELLOW,
        GREEN
    }

    enum Weekday {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    enum AlertLevel {
        LOW,
        MODERATE,
        HIGH,
        SEVERE
    }

    public static void main(String[] args) {
        CoffeeSize size = CoffeeSize.LARGE;
        System.out.println(size);

        SeatSection general = SeatSection.GENERAL;
        SeatSection premium = SeatSection.PREMIUM;
        SeatSection vip = SeatSection.VIP;

        System.out.println(general + " is assigned value: " + general.ordinal());
        System.out.println(premium + " is assigned value: " + premium.ordinal());
        System.out.println(vip + " is assigned value: " + vip.ordinal());

        TrafficLight[] signals = TrafficLight.values();
        System.out.println(signals[1]);

        Weekday workDay = Weekday.WEDNESDAY;
//        Weekday saturday = Weekday.SATURDAY;
//        Weekday sunday = Weekday.SUNDAY;
        boolean isWeekend = (workDay == Weekday.SATURDAY || workDay == Weekday.SUNDAY);

        System.out.println("Workday selected:  " + workDay);
        System.out.println("Is it a weekend? " + isWeekend);

        AlertLevel currentAlert = AlertLevel.HIGH;
        System.out.println(currentAlert);

        switch (currentAlert) {
            case LOW:
                System.out.println("No immediate danger.");
                break;
            case MODERATE:
                System.out.println("Stay alert and aware.");
                break;
            case HIGH:
                System.out.println("Take precautions and stay informed.");
                break;
            case SEVERE:
                System.out.println("Immediate action required!");
                break;


        }
    }
}
