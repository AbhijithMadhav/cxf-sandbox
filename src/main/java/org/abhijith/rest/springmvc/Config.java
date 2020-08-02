package org.abhijith.rest.springmvc;


import org.abhijith.rest.cxf.EmployeeResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(name = "employeeResource")
    public EmployeeResource employeeResource() {
        return new EmployeeResource();
    }
}
