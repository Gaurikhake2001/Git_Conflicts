package com.gk.controller;

import com.gk.entity.Employee;
//import com.gk.helper.Helper;
import com.gk.service.EmployeeService;
//import com.gk.serviceimpl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/emp-api/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

//    @Autowired
//    private Helper helper;

    @Autowired
    private Environment environment;

@GetMapping("/getDetails")
public String showProperties()
{
    return "Port Number: " + environment.getProperty("server.port") + "\n" +"Application Name: " +
            environment.getProperty("spring.application.name") + "\n" +
            "Database Url:" + environment.getProperty("spring.datasource.url");

}

@Value("${spring.datasource.driver-class-name}")
private String dataSource;

@GetMapping("/getDetailsViaValue")
public String getDetailsViavalue()
{
 return dataSource;
}

    //    insert data into DB
    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee){

        return new ResponseEntity<Employee>(employeeService.createEmployee(employee), HttpStatus.CREATED);

    }

    @GetMapping("/getEmployee/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int empId){

        return new ResponseEntity<>(employeeService.getEmployee(empId), HttpStatus.OK);
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<Employee>> getAllEmployee(){
         return new ResponseEntity<>(employeeService.getAllEmployee() , HttpStatus.OK);
    }

    @PutMapping("/updateEmployee/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int empId, @RequestBody Employee employee)
    {
        return new ResponseEntity<>(employeeService.updateEmployee(empId, employee), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteEmployee/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int empId)
    {
        return new ResponseEntity<>(employeeService.deleteEmployee(empId), HttpStatus.OK);
    }


//    method to upload file
//    @PostMapping("/upload-file")
//
//    public ResponseEntity<String> uploadfile(@RequestParam("file")MultipartFile multipartFile)
//    {
//        try
//        {
////            upload file
//            boolean success = helper.uploadfiles(multipartFile);
//            if(success)
//            {
////                return ResponseEntity.ok("Successfully Uploaded");
//
////                To return path of file
//                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/get/").path(multipartFile.getOriginalFilename()).toUriString());
//
//            }
//
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
//    }
}
