package org.example;
import java.util.Scanner;
public class App
{
    static IO io = new IO();
    public Scanner console = new Scanner(System.in);
    static boolean isCurator;
    public static void main( String[] args ) {
        Artifact entered;

        String artifactName = io.getNonEmptyString("Enter Artifact name: ");
        int yearOfDiscovery = io.getInt("Enter the year of it's discovery: ",1000, 5000);
        String discovererFN = io.getNonEmptyString("Enter the discoverer's first name: ");
        String discovererLN = io.getNonEmptyString("Enter the discoverer's last name: ");
        String specialty = io.getNonEmptyString("Enter the discoverer's specialty: ");
        String isCuratorStr = io.getNonEmptyString("Is the discoverer the curator? (y/n): ");
        Person discoverer = new Person(discovererFN, discovererLN, specialty);
        if(isCuratorStr.equals("y")){
            isCurator = true;
            Person curator = discoverer;
            entered = new Artifact(artifactName, discoverer, curator, yearOfDiscovery);
        }else {
            isCurator = false;
            String curatorFN = io.getNonEmptyString("Enter the curator's first name: ");
            String curatorLN = io.getNonEmptyString("Enter the curator's last name: ");
            String curatorSpecialty = io.getNonEmptyString("Enter the curator's specialty: ");
            Person curator = new Person(curatorFN, curatorLN, curatorSpecialty);
            entered = new Artifact(artifactName, discoverer, curator, yearOfDiscovery);
        }
        if (isCurator){
            io.report1(entered);
        }else{
            io.report2(entered);
        }









    }

}
