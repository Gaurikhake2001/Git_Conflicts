package com.gk.serviceimpl;

import com.gk.entity.Employee;
import com.gk.repository.EmployeeRepository;
import com.gk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(int empId) {
        return employeeRepository.findById(empId).orElseThrow(()-> new NoSuchElementException("Element not found! with id "+ empId)) ;

    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(int empId, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(empId).get();


        if (existingEmployee != null){

            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setName(employee.getName());
            existingEmployee.setPassword(employee.getPassword());
            existingEmployee.setPhoneNo(employee.getPhoneNo());


        return employeeRepository.save(existingEmployee);
    }
        else
            return new Employee();
    }

    @Override
    public String deleteEmployee(int empId) {
      if(employeeRepository.existsById(empId)) {
          employeeRepository.deleteById(empId);
          return "id deleted : "+ empId;
      }
      else{
          return "id not found : " + empId;
        }

    }
}
