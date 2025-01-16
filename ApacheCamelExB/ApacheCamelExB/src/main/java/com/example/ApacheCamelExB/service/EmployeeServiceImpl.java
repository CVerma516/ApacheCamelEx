package com.example.ApacheCamelExB.service;

import com.example.ApacheCamelExB.model.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl extends RouteBuilder {

    @Autowired
    DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Override
    public void configure() throws Exception {
        // INSERT Route
        from("direct:insertEmployee")
                .routeId("insertEmployeeRoute")
                .setBody(simple("INSERT INTO employee (emp_id, emp_name) VALUES ('${body.empId}', '${body.empName}')"))
                .to("jdbc:dataSource")
                .log("Inserted Employee: ${body}");

        // SELECT Route
        from("direct:getEmployees")
                .routeId("getEmployeesRoute")
                .setBody(constant("SELECT * FROM employee"))
                .to("jdbc:dataSource")
                .log("Fetched Employees: ${body}");
    }
}


