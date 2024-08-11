package com.gk;

import com.gk.entity.Employee;
import com.gk.repository.EmployeeRepository;
import com.gk.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setEmpId(1);
        employee.setName("Gauri");
        employee.setPassword("pass@123");
        employee.setEmail("gauri@gmail.com.com");
        employee.setPhoneNo(1234567890L);

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee createdEmployee = employeeService.createEmployee(employee);

        assertEquals(employee, createdEmployee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGetEmployee() {
        int empId = 1;
        Employee employee = new Employee();
        employee.setEmpId(empId);
        employee.setName("Gauri");
        employee.setPassword("pass@123");
        employee.setEmail("gauri@gmail.com");
        employee.setPhoneNo(1234567890L);

        when(employeeRepository.findById(empId)).thenReturn(Optional.of(employee));

        Employee fetchedEmployee = employeeService.getEmployee(empId);

        assertEquals(employee, fetchedEmployee);
        verify(employeeRepository, times(1)).findById(empId);
    }

    @Test
    public void testGetEmployee_NotFound() {
        int empId = 1;

        when(employeeRepository.findById(empId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> employeeService.getEmployee(empId));
        verify(employeeRepository, times(1)).findById(empId);
    }

    @Test
    public void testGetAllEmployee() {
        Employee employee1 = new Employee();
        employee1.setEmpId(1);
        employee1.setName("Gauri");
        employee1.setPassword("pass@123");
        employee1.setEmail("Gauri@gmail.com");
        employee1.setPhoneNo(1234567890L);

        Employee employee2 = new Employee();
        employee2.setEmpId(2);
        employee2.setName("xyz");
        employee2.setPassword("password");
        employee2.setEmail("xyz@example.com");
        employee2.setPhoneNo(9876543210L);

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> fetchedEmployees = employeeService.getAllEmployee();

        assertEquals(employees, fetchedEmployees);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateEmployee() {
        int empId = 1;
        Employee existingEmployee = new Employee();
        existingEmployee.setEmpId(empId);
        existingEmployee.setName("Gauri");
        existingEmployee.setPassword("pass@123");
        existingEmployee.setEmail("gauri@gmail.com.com");
        existingEmployee.setPhoneNo(1234567890L);

        Employee newEmployee = new Employee();
        newEmployee.setEmpId(empId);
        newEmployee.setName("Gauri Khake");
        newEmployee.setPassword("newpassword");
        newEmployee.setEmail("gaurik@gmail.com");
        newEmployee.setPhoneNo(9876543210L);

        when(employeeRepository.findById(empId)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);

        Employee updatedEmployee = employeeService.updateEmployee(empId, newEmployee);

        assertEquals(newEmployee.getName(), updatedEmployee.getName());
        assertEquals(newEmployee.getPassword(), updatedEmployee.getPassword());
        assertEquals(newEmployee.getEmail(), updatedEmployee.getEmail());
        assertEquals(newEmployee.getPhoneNo(), updatedEmployee.getPhoneNo());
        verify(employeeRepository, times(1)).findById(empId);
        verify(employeeRepository, times(1)).save(existingEmployee);
    }

    @Test
    public void testUpdateEmployee_NotFound() {
        int empId = 1;
        Employee newEmployee = new Employee();
        newEmployee.setEmpId(empId);
        newEmployee.setName("John Smith");
        newEmployee.setPassword("newpassword");
        newEmployee.setEmail("john.smith@example.com");
        newEmployee.setPhoneNo(9876543210L);

        when(employeeRepository.findById(empId)).thenReturn(Optional.empty());

        Employee updatedEmployee = employeeService.updateEmployee(empId, newEmployee);

        assertNull(updatedEmployee.getName());
        verify(employeeRepository, times(1)).findById(empId);
        verify(employeeRepository, times(0)).save(any(Employee.class));
    }

    @Test
    public void testDeleteEmployee() {
        int empId = 1;

        when(employeeRepository.existsById(empId)).thenReturn(true);

        String response = employeeService.deleteEmployee(empId);

        assertEquals("id deleted : " + empId, response);
        verify(employeeRepository, times(1)).existsById(empId);
        verify(employeeRepository, times(1)).deleteById(empId);
    }

    @Test
    public void testDeleteEmployee_NotFound() {
        int empId = 1;

        when(employeeRepository.existsById(empId)).thenReturn(false);

        String response = employeeService.deleteEmployee(empId);

        assertEquals("id not found : " + empId, response);
        verify(employeeRepository, times(1)).existsById(empId);
        verify(employeeRepository, times(0)).deleteById(empId);
    }
}

