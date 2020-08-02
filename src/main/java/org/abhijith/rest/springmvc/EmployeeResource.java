package org.abhijith.rest.springmvc;

import org.abhijith.rest.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RequestMapping("/employees")
@RestController("org.abhijith.rest.springmvc.EmployeeResource")
public class EmployeeResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeResource.class);
	@RequestMapping(method = RequestMethod.GET, value = "/{name}")
	public Employee get(@PathVariable String name) {
		LOGGER.debug("Name : {}", name);
		return new Employee(name, Employee.EmployeeType.PERMANENT.name(), Collections.emptyList());
		//return null;
	}
}
