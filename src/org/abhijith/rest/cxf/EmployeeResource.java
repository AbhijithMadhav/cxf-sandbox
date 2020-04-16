package org.abhijith.rest.cxf;


import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.abhijith.rest.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@Path("/employees")
public class EmployeeResource {

	private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeResource.class);

	@GET
	@Path("/search")
	@Produces({
		APPLICATION_JSON,
		APPLICATION_XML,
		"application/yaml", 
		"application/x-jackson-smile"
		})
	public Employee search(
			@QueryParam("name") String name,
			@DefaultValue("permanent") @QueryParam("type") String type, 
			@QueryParam("department") List<String> departments) {
		LOGGER.debug("IN HERE : {}");
		return new Employee(name, type, departments);
	}
	
	@GET
	@Path("/{id: [0-9]+}")
	@Produces({
		APPLICATION_JSON,
		APPLICATION_XML,
		"application/yaml", 
		"application/x-jackson-smile"
		})
	public Employee get(@PathParam("id") Integer id, 
			@HeaderParam("Accept") String accept, 
			@Context HttpHeaders headers,
			@Context Request request,
			@CookieParam("name") String value) {
		LOGGER.debug("Accept : {}", accept);
		LOGGER.debug("Cookies : [name = {}]", value);
		for (String name : headers.getCookies().keySet())
			LOGGER.debug(headers.getCookies().get(name).getValue());
		return new Employee("some_name" + id, "contract", Arrays.asList("Finance", "Security"));
	}
	
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	@POST
	public Response put(Employee employee) {
		Cookie cookie = new Cookie("name", "some_cookie_value");
		NewCookie newCookie = new NewCookie(cookie);
		return Response.status(Status.ACCEPTED).entity(employee).cookie(newCookie).build();
	}

}
