package com.gk;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gk.entity.Employee;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.*;
//
//import static org.assertj.core.api.Java6Assertions.assertThat;
//
//@SpringBootTest
//public class EmployeeControllerTest {
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    //    valid method for postmapping of employee
//    @Test
//    public void isValidEmployee() throws Exception
//    {
//        Employee employee = new Employee();
//        employee.setName("Gauri");
//        employee.setPassword("g@123");
//        employee.setEmail("gk@gmail.com");
//        employee.setPhoneNo(9638527410L);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(employee), httpHeaders);
//
//        ResponseEntity<Employee> response = testRestTemplate.postForEntity("/emp-api/createEmployee", request, Employee.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isNotNull();
//        assertThat(response.getBody().getName()).isEqualTo("Gauri");
//        assertThat(response.getBody().getPassword()).isEqualTo("g@123");
//        assertThat(response.getBody().getEmail()).isEqualTo("gk@gmail.com");
//        assertThat(response.getBody().getPhoneNo()).isEqualTo(9638527410L);
//    }
//
//    @Test
//    public void invalidEmployee() throws Exception {
//        Employee employee = new Employee();
//        employee.setName("");
//        employee.setPassword("***");
//        employee.setEmail("email");
//        employee.setPhoneNo(1234567890L);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(employee), headers);
//
//        ResponseEntity<String> response = testRestTemplate.postForEntity("/emp-api/createEmployee", request, String.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//    }
//
//}

import com.gk.controller.EmployeeController;
import com.gk.entity.Employee;
import com.gk.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setEmpId(1);
        employee.setName("Gauri");
        employee.setPassword("pass@123");
        employee.setEmail("gauri@gmail.com");
        employee.setPhoneNo(1234567890L);

        when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.createEmployee(employee);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employee, response.getBody());
        verify(employeeService, times(1)).createEmployee(employee);
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

        when(employeeService.getEmployee(empId)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.getEmployee(empId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
        verify(employeeService, times(1)).getEmployee(empId);
    }

    @Test
    public void testGetAllEmployee() {
        Employee employee1 = new Employee();
        employee1.setEmpId(1);
        employee1.setName("gauri");
        employee1.setPassword("pass@123");
        employee1.setEmail("gauri@gmail.com");
        employee1.setPhoneNo(1234567890L);

        Employee employee2 = new Employee();
        employee2.setEmpId(2);
        employee2.setName("Gauri");
        employee2.setPassword("pass@123");
        employee2.setEmail("gauri@gmail.com");
        employee2.setPhoneNo(9876543210L);

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.getAllEmployee()).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.getAllEmployee();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
        verify(employeeService, times(1)).getAllEmployee();
    }

    @Test
    public void testUpdateEmployee() {
        int empId = 1;
        Employee employee = new Employee();
        employee.setEmpId(empId);
        employee.setName("Gauri");
        employee.setPassword("pass@123");
        employee.setEmail("gauri@gmail.com");
        employee.setPhoneNo(1234567890L);

        when(employeeService.updateEmployee(eq(empId), any(Employee.class))).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.updateEmployee(empId, employee);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(employee, response.getBody());
        verify(employeeService, times(1)).updateEmployee(eq(empId), any(Employee.class));
    }

    @Test
    public void testDeleteEmployee() {
        int empId = 1;
        String expectedResponse = "Employee deleted successfully";

        when(employeeService.deleteEmployee(empId)).thenReturn(expectedResponse);

        ResponseEntity<String> response = employeeController.deleteEmployee(empId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(employeeService, times(1)).deleteEmployee(empId);
    }
}
