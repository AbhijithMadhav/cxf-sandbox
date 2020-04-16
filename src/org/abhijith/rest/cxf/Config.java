package org.abhijith.rest.cxf;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public EmployeeResource employeeResource() {

        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        CustomerService cs = new CustomerService();
        sf.setServiceBeans(cs);
        sf.setAddress("http://localhost:9080/");
        sf.create();
        return new EmployeeResource();
    }
}
