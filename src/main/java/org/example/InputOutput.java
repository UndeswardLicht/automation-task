package org.example;
import java.io.IOException;
import java.util.Scanner;

public class InputOutput {

    public static void printOutEmployees() throws IOException  {
        for (Employee employee : FileUtils.readCollectionFromFile()){
            System.out.println(employee.toString());
        }
    }

    public static Employee createEmployeeFromInput(String[] input) throws RuntimeException {
        while(!validateSpecialization(input[2])){
            System.out.println("Specialization can be only Manager or Engineer");
            input = scanEmployeeData();
        }
        Employee employee;
        employee = new Employee(
                    input[0],
                    Integer.parseInt(input[1]),
                    input[2],
                    Integer.parseInt(input[3]),
                    input[4],
                    input[5]
            );
        return employee;
    }

    public static String[] scanEmployeeData(){
        System.out.println("Enter the NAME,AGE,SPECIALIZATION (Manager or Engineer)," +
                "EXPERIENCE,POSITION,FAMILY STATUS separated by space");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput.split(" ");
    }

    public static boolean validateSpecialization(String specialization) {
        return specialization.equals("Manager") ||
                specialization.equals("Engineer");
    }

}
