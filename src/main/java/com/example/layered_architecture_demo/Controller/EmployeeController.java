package com.example.layered_architecture_demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.layered_architecture_demo.DTO.EmployeeDTO;
import com.example.layered_architecture_demo.Service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Management")
@SecurityRequirement(name = "BearerAuth")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // cach 1: tao phuong thuc de tra ve thong bao loi
    // cach 2: dung message trong annotaion o DTO, goi getDefaultMessage() de lay ra
    // thong bao trong api
    // phuong thuc tra ve thong bao loi
    // private String errorResult(String fieldName) {
    // switch (fieldName) {
    // case "salary":
    // return "Luong phai lon hon 0";
    // case "name":
    // return "Ten nhan vien khong duoc de trong";
    // case "age":
    // return "Tuoi phai lon hon 18";
    // case "email":
    // return "Email khong dung dinh dang";
    // default:
    // return "Truong " + fieldName + " khong hop le";
    // }
    // }

    @Operation
    @GetMapping("/all")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // chua trien khai exception handling

    // @Operation
    // @PostMapping("/add")
    // public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDTO
    // employee, BindingResult result) {
    // if (result.hasErrors()) {
    // Map<String, String> errors = new HashMap<>();
    // result.getFieldErrors().forEach(
    // error -> errors.put(error.getField(), error.getDefaultMessage()));
    // return ResponseEntity.badRequest().body(errors);
    // }
    // EmployeeDTO employeeSaved = employeeService.addEmployee(employee);
    // return ResponseEntity.status(201).body(employeeSaved);
    // }

    // da trien khai exception handling
    @Operation
    @PostMapping("/add")
    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employee) {
        EmployeeDTO employeeSaved = employeeService.addEmployee(employee);
        return ResponseEntity.status(201).body(employeeSaved);
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
