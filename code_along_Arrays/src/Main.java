//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Arrays Demo - Classroom Manager");
        // start with 5 students
        String s1 = "Jerry";
        String s2 = "Elaine";
        String s3 = "George";
        String s4 = "Kramer";
        String s5 = "Newman";
        // do stuff with the students
        // print welcome to each
        System.out.println("Hello, " + s1);
        System.out.println("Hello, " + s2);
        System.out.println("Hello, " + s3);
        System.out.println("Hello, " + s4);
        System.out.println("Hello, " + s5);

        //This would be better in an array!
        // creating an array
        String[] students;
        students = new String[5];
        students[0] = "Jerry";
        students[1] = "Elaine";
        students[2] = "George";
        students[3] = "Kramer";
        students[4] = "Newman";
        //students[5] = "Susan";
        double[] grades = {88.5, 97.8, 92.2, 78.7, 80.1};
        //accessing arrays
        System.out.println("3rd student = " + students[2]);
        System.out.println("Last student = " + students[students.length - 1]);
        // print list of students and their grades
        for (int i = 0; i < students.length; i++) {
            System.out.println("Student[" + i + "] = "
                    + students[i] + ", grade = " + grades[i]);
        }
        // average grade?
        double sum = 0.0;
        for (int i = 0; i < grades.length; i++) {
            sum += grades[i];
        }
        System.out.println("Grade average = "+ (sum /
                grades.length));


    }
}
