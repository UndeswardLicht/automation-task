package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FileAndInputOutputTests {

    @Test
    void createEmployeeFromInputSuccessfully() {
        String[] input = {"Johannes", "30", "Manager", "10", "Lead", "Kids"};
        Employee expectedEmployee = new Employee("Johannes", 30, "Manager",
                10, "Lead", "Kids");
        Employee actualEmployee = InputOutput.createEmployeeFromInput(input);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void exceptionIfAgeIsNotANumber() {
        String[] input = {"Johannes", "Vasa", "Manager", "10", "Lead", "Kids"};
        assertThrows(NumberFormatException.class,
                () -> InputOutput.createEmployeeFromInput(input));
    }

    @Test
    void exceptionIfExperienceIsNotANumber() {
        String[] input = {"Johannes", "30", "Manager", "Nice", "Lead", "Kids"};
        assertThrows(NumberFormatException.class,
                () -> InputOutput.createEmployeeFromInput(input));
    }
    @Test
    void arrayOutOfBoundExceptionIfLessArguments(){
        String[] input = {"Johannes", "30"};
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> InputOutput.createEmployeeFromInput(input));
    }

    @Test
    void trueIfSpecializationIsEngineer() {
       assertTrue(InputOutput.validateSpecialization("Engineer"));
    }

    @Test
    void trueIfSpecializationIsManager() {
        assertTrue(InputOutput.validateSpecialization("Manager"));
    }
    @Test
    void falseIfSpecializationIsElse() {
        assertFalse(InputOutput.validateSpecialization("Developer"));
    }

    @Test
    void writeEmployeeToFileSuccessfully() throws FileNotFoundException {
        FileUtils.EMPLOYEES_CSV_PATH = "src/test/resources/fileForWriting.csv";
        Scanner scanner = new Scanner(new FileReader(FileUtils.EMPLOYEES_CSV_PATH));
        String expectedString = "Nom,42,Manager,23,Lead,married";

        Employee testEmployee = new Employee("Nom",42, "Manager",
                23,"Lead","married");
        FileUtils.writeToFile(testEmployee);

        assertEquals(expectedString, scanner.nextLine());
    }

    @Test
    void readCollectionOfEmployeesFromFileSuccessfully() throws IOException{
        FileUtils.EMPLOYEES_CSV_PATH = "src/test/resources/fileForReading.csv";
        List<Employee> actualEmployees = FileUtils.readCollectionFromFile();
        List<Employee> expectedEmployees = new ArrayList<>();

        expectedEmployees.add(new Employee("John",10,"Engineer",0,"JunJunior","kids"));
        expectedEmployees.add(new Employee("Smith",20,"Manager",1,"Junior","kids"));
        expectedEmployees.add(new Employee("Yui",30,"Engineer",10,"Lead","wife"));

        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    void exceptionIfNoFileToReadFrom(){
        FileUtils.EMPLOYEES_CSV_PATH = "src/test/resources/noFile.csv";
        assertThrows(FileNotFoundException.class,
                FileUtils::readCollectionFromFile);
    }
    @Test
    void exceptionIfNoFileToDeleteFrom(){
        FileUtils.EMPLOYEES_CSV_PATH = "src/test/resources/noFile.csv";
        assertThrows(FileNotFoundException.class,
                () -> {
            FileUtils.deleteFromFile("Gregory");
                });
    }

    @Test
    void returnsEmptyCollectionIfFileToReadIsEmpty() throws IOException {
        FileUtils.EMPLOYEES_CSV_PATH = "src/test/resources/emptyFile.csv";
        assertTrue(FileUtils.readCollectionFromFile().isEmpty());
    }

    @Test
    void deletingNonExistingRowThrowsException() throws IOException {
        String expectedString = "Yui,30,Engineer,10,Lead,wife";
        FileUtils.EMPLOYEES_CSV_PATH = "src/test/resources/fileForDeleting.csv";
        Scanner scanner = new Scanner(new FileReader(FileUtils.EMPLOYEES_CSV_PATH));

        FileUtils.deleteFromFile("Garrison");
        assertEquals(expectedString, scanner.nextLine());

    }


    @Test
    void deleteFromFileSuccessfully() throws IOException {
        FileUtils.EMPLOYEES_CSV_PATH = "src/test/resources/fileForDeleting.csv";
        Scanner scanner = new Scanner(new FileReader(FileUtils.EMPLOYEES_CSV_PATH));

        String expectedString = "Yui,30,Engineer,10,Lead,wife";
        FileUtils.deleteFromFile("Smith");
        assertEquals(expectedString, scanner.nextLine());

        BufferedWriter writer = new BufferedWriter(
                new FileWriter(FileUtils.EMPLOYEES_CSV_PATH, true));
            writer.write("Smith,20,Manager,1,Junior,kids");
        writer.close();
    }

}