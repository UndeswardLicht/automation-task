package org.example;

import java.io.IOException;
import java.util.Scanner;

import static org.example.InputOutput.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                Good day! This is a demo-program data-base for employees.
                To get the list of employees print 'get'. To add an employee, print 'add'.
                To delete an employee, print 'delete'. To exit the program print anything else.""");

        switch (scanner.nextLine()){
            case "get":
                printOutEmployees();
                break;
            case "add":
                try{
                    Employee employee = createEmployeeFromInput(scanEmployeeData());
                    FileUtils.writeToFile(employee);
                }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                    System.out.println("Make sure AGE and EXPERIENCE are numbers");
                    break;
                }
                System.out.println("Employee added!");
                break;
            case "delete":
                System.out.println("Print the name of the employee to delete them");
                FileUtils.deleteFromFile(scanner.nextLine());
                System.out.println("Deleted!");
            default:
                break;
        }
    }
}