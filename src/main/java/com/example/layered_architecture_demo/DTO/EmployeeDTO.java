package com.example.layered_architecture_demo.DTO;

import jakarta.validation.constraints.PositiveOrZero;

public class EmployeeDTO {
    private Long id;
    private String name;
    private int age;

    @PositiveOrZero(message = "Salary must be greater than or equal to 0")
    private double salary;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
