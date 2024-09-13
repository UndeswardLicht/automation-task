package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static String EMPLOYEES_CSV_PATH = "src/main/resources/file.csv";
    public static void writeToFile(Employee employee){
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(EMPLOYEES_CSV_PATH, true));
            writer.write(employee.toString()+ "\n");
            writer.close();
        }catch (IOException e){
            System.out.println("Was not able to write to the file");
        }
    }

    public static List<Employee> readCollectionFromFile() throws IOException {
        String line;
        String[] splitLine;
        Employee employee;
        List<Employee> employees = new ArrayList<>();

            BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEES_CSV_PATH));
            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",");
                employee = new Employee(
                        splitLine[0],
                        Integer.parseInt(splitLine[1]),
                        splitLine[2],
                        Integer.parseInt(splitLine[3]),
                        splitLine[4],
                        splitLine[5]
                );
                employees.add(employee);
            }
            reader.close();

        return employees;
    }

    public static void deleteFromFile(String name) throws IOException {
        //удалять будем по имени
        List<Employee> employees = readCollectionFromFile();
        employees.removeIf(employee -> employee.getName().equals(name));
        flushEverythingFromFile();
        for (Employee employee : employees) {
            writeToFile(employee);
        }
    }

    private static void flushEverythingFromFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile(EMPLOYEES_CSV_PATH,"rw");
        raf.setLength(0);

    }
}
