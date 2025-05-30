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

        //Part 2: Book Library System
        Book book1 = new Book("The Hobbit", "J.R.R Tolkien", true);
        book1.displayBook();
        book1.borrowBook();
        book1.displayBook();

        // Part 3: Shared Account Reference
        BankAccount acc1 = new BankAccount("Alice", 1000.0);
        BankAccount acc2 = acc1;
        acc2.display_Account();
        System.out.println("Depositing $500 to acc2...");
        acc2.deposit(500);
        acc1.display_Account();

        //Part 4: Employee Tracking
        new Employee("John");
        new Employee("Jane");
        new Employee("Mike");
        System.out.println("Total Employees: " + Employee.getTotalEmployees());
    }
}

