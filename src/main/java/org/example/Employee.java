package org.example;

import java.util.Objects;

public class Employee {

    private String name;
    private int age;
    private String specialization;
    private int experience;
    private String position;
    private String familyStatus;


    public Employee(String name, int age, String specialization,
                    int experience, String position, String familyStatus) {
        this.name = name;
        this.age = age;
        this.specialization = specialization;
        this.experience = experience;
        this.position = position;
        this.familyStatus = familyStatus;
    }

    @Override
    public String toString() {
        char separator = ',';
        return name +
                separator + age +
                separator + specialization +
                separator + experience +
                separator + position +
                separator + familyStatus;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Employee employee)) return false;
        return age == employee.age && experience == employee.experience && Objects.equals(name, employee.name) && Objects.equals(specialization, employee.specialization) && Objects.equals(position, employee.position) && Objects.equals(familyStatus, employee.familyStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, specialization, experience, position, familyStatus);
    }
}
