package org.abhijith.rest.cxf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public EmployeeResource employeeResource() {
        return new EmployeeResource();
    }
}
