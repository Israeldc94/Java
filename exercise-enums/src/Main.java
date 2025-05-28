//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    enum CoffeeSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    enum SeatSection{
        GENERAL,
        PREMIUM,
        VIP
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

        }
    }
