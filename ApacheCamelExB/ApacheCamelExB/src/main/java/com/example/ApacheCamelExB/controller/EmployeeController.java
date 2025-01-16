package com.example.ApacheCamelExB.controller;


import com.example.ApacheCamelExB.model.Employee;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping("/add")
    public String insertEmployee(@RequestBody Employee employee) {
        producerTemplate.sendBody("direct:insertEmployee", employee);
        return "Employee inserted successfully!";
    }

    @GetMapping("/")
    public Object getAllEmployees() {
        return producerTemplate.requestBody(Optional.of("direct:getEmployees"), null);
    }
}