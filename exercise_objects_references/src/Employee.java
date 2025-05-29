public class Employee {
    private String name;
    private static int totalEmployees = 0;

    public Employee(String name){
        this.name = name;
        totalEmployees++;
    }

    public static int getTotalEmployees(){
        return totalEmployees;
    }
}

