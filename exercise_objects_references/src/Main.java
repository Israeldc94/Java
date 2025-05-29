//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Part 1: Car Dealership System
        Car car1 = new Car("Toyota", "Corolla", 2020);
        Car car2 = new Car("Ford", "Mustang", 2022);
        System.out.print("Car1: ");
        car1.displayInfo();
        System.out.print("Car2: ");
        car2.displayInfo();
    }
}

