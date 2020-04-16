import java.util.Collections;

import javax.ws.rs.core.MediaType;

import org.abhijith.rest.cxf.EmployeeResource;
import org.abhijith.rest.models.Employee;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;
import com.fasterxml.jackson.jaxrs.yaml.JacksonYAMLProvider;

public class TestResourceForCXF {
	
	private static Server server;
	private static final String BASE_ADDRESS = "http://localhost:8080/cxf-sandbox/cxf-servlet";
	
	@BeforeClass
	public static void startServer() {
		JAXRSServerFactoryBean sFactoryBean = new JAXRSServerFactoryBean();
		sFactoryBean.setResourceClasses(EmployeeResource.class);
		sFactoryBean.setProvider(new JacksonJsonProvider());
		sFactoryBean.setAddress(BASE_ADDRESS);
		server = sFactoryBean.create();
	}
	
	@Test
	public void test() {
		WebClient client = WebClient.create(BASE_ADDRESS).accept("application/json").path("/employees/12");
	    Employee employee = client.get(Employee.class);
	    System.out.println(employee);
	    System.out.println(client.getResponse().getStatus());
	}
	
	@AfterClass
	public static void stopServer() {
		server.stop();
		server.destroy();
	}
}