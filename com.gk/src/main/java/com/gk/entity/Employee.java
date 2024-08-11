package com.gk.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Employee_Details")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "Password is required")
    private String password;

    @Email
    private String email;

    @NotNull
    private long phoneNo;
    @NotNull
    private String address;
}
