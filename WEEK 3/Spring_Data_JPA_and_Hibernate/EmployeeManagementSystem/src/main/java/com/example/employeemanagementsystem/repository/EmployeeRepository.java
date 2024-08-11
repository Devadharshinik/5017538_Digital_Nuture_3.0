package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.Employee;
import com.example.employeemanagementsystem.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentName(String departmentName);

    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
    List<Employee> searchByName(@Param("name") String name);
}
