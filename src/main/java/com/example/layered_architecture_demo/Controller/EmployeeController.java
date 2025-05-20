package com.example.layered_architecture_demo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.layered_architecture_demo.DTO.EmployeeDTO;
import com.example.layered_architecture_demo.Service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Management")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation
    @GetMapping("/all")
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @Operation
    @PostMapping("/add")
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.addEmployee(employee);
    }

    @Operation
    @PutMapping("/update/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employee) {
        return employeeService.updaEmployeeDTO(id, employee);
    }

    @Operation
    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @Operation
    @GetMapping("/name/{name}")
    public EmployeeDTO getEmployeeByName(@PathVariable String name) {
        return employeeService.getEmployeeByName(name);
    }

    @Operation
    @GetMapping("/salary")
    public List<EmployeeDTO> getEmployeesBySalaryRange(@RequestParam double min, @RequestParam double max) {
        return employeeService.getEmployeesBySalaryRange(min, max);
    }

    @Operation
    @GetMapping("/nameAndAge")
    public List<EmployeeDTO> getEmployeesByNameAndAge(@RequestParam String name, @RequestParam int age) {
        return employeeService.getEmployeesByNameAndAge(name, age);
    }
}
