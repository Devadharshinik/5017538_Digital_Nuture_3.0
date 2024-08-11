package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.Employee;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

    @GetMapping("/page")
    public Page<Employee> getEmployeesWithPagination(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @GetMapping("/sorted")
    public List<Employee> getEmployeesWithSorting(@RequestParam String sortBy) {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
    }

}
