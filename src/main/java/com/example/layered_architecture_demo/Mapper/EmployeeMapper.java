package com.example.layered_architecture_demo.Mapper;

import org.springframework.stereotype.Component;

import com.example.layered_architecture_demo.DTO.EmployeeDTO;
import com.example.layered_architecture_demo.Model.Employee;

@Component
public class EmployeeMapper {
    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSalary(employee.getSalary());
        dto.setAge(employee.getAge());
        return dto;
    }
    public Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setSalary(dto.getSalary());
        employee.setAge(dto.getAge());
        return employee;
    }
    public void updateEntityFromDTO(EmployeeDTO dto, Employee employee) {
        employee.setName(dto.getName());
        employee.setSalary(dto.getSalary());
        employee.setAge(dto.getAge());
    }   
}
