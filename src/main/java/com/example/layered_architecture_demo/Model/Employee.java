package com.example.layered_architecture_demo.Model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private double salary;

    @ManyToMany
    @JoinTable(name = "employee_department", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "department_id"))
    private Set<Department> departments = new HashSet<>();

    public Employee() {
    }

    public Employee(Long id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

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

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    // Helper methods
    public void addDepartment(Department department) {
        this.departments.add(department);
        department.getEmployees().add(this);
    }

    public void removeDepartment(Department department) {
        this.departments.remove(department);
        department.getEmployees().remove(this);
    }

}
