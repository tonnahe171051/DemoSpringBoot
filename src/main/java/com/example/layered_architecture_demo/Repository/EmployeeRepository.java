package com.example.layered_architecture_demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.layered_architecture_demo.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByName(String name);
}
