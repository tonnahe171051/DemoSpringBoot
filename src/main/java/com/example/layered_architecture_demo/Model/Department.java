package com.example.layered_architecture_demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;

    @ManyToMany(mappedBy = "departments")
    @JsonIgnore // Ngăn đệ quy vô hạn khi serialize JSON
    private Set<Employee> employees = new HashSet<>();

    // Constructors
    public Department() {
    }

    public Department(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    // Getters and Setters
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}