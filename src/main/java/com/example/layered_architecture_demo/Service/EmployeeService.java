package com.example.layered_architecture_demo.Service;

import java.util.List;

import com.example.layered_architecture_demo.DTO.EmployeeDTO;


public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO addEmployee(EmployeeDTO employee);
    EmployeeDTO updaEmployeeDTO(Long id, EmployeeDTO employee);
    EmployeeDTO getEmployeeByName(String name);
    List<EmployeeDTO> getEmployeesBySalaryRange(double min, double max);
    List<EmployeeDTO> getEmployeesByNameAndAge(String name, int age);
    void deleteEmployee(Long id);

}
    