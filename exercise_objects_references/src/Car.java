public class Car {
    private String brand;
    private  String model;
    private int year;

    public Car(String brand, String model, int year){
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public void  displayInfo() {
        System.out.printf("%s, %s, (%d) %n", this.brand, this.model, this.year);
    }
}
