package com.example.layered_architecture_demo.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.layered_architecture_demo.DTO.EmployeeDTO;
import com.example.layered_architecture_demo.Mapper.EmployeeMapper;
import com.example.layered_architecture_demo.Model.Employee;
import com.example.layered_architecture_demo.Repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
            .map(employeeMapper::toDTO)
            .collect(Collectors.toList());
    }
    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employee) {
        Employee employeeEntity = employeeMapper.toEntity(employee);
        Employee savEmployee = employeeRepository.save(employeeEntity);
        return employeeMapper.toDTO(savEmployee);
    }
    @Override
    public EmployeeDTO updaEmployeeDTO(Long id, EmployeeDTO employee) {
        Employee employeeEntity = employeeRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Employee not found with id: " + id)
        );
        employeeMapper.updateEntityFromDTO(employee, employeeEntity);
        Employee updatedEmployee = employeeRepository.save(employeeEntity);
        return employeeMapper.toDTO(updatedEmployee);
    }
    @Override
    public void deleteEmployee(Long id) {
        Employee employeeEntity = employeeRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Employee not found with id: " + id)
        );
        employeeRepository.delete(employeeEntity);
    }
    @Override
    public EmployeeDTO getEmployeeByName(String name) {
        Employee employeeEntity = employeeRepository.findByName(name);
        if (employeeEntity == null) {
            throw new RuntimeException("Employee not found with name: " + name);
        }
        return employeeMapper.toDTO(employeeEntity);
    }
    
}
