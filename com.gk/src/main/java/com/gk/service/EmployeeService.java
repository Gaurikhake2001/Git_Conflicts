package com.gk.service;

import com.gk.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    Employee getEmployee(int empId);

    List<Employee> getAllEmployee();

    Employee updateEmployee(int empId, Employee employee);

    String deleteEmployee(int empId);
}
