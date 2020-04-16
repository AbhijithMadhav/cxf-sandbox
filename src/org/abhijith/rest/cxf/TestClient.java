package org.abhijith.rest.cxf;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.abhijith.rest.models.Employee;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSInvoker;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.service.invoker.Invoker;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.mockito.Mockito;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class TestClient {

	private static Server server;
	private static final String BASE_ADDRESS = "http://localhost:8080/cxf-sandbox/cxf-servlet";

	private static void startServer() {
		JAXRSServerFactoryBean sFactoryBean = new JAXRSServerFactoryBean();
		sFactoryBean.setInvoker(new Invoker() {

			Invoker jarsInvoker = new JAXRSInvoker();

			@Override
			public Object invoke(Exchange exchange, Object o) {

				HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
				Mockito.when(request.getRemoteHost()).thenReturn("host");
				exchange.getInMessage().put(AbstractHTTPDestination.HTTP_REQUEST, request);
				return jarsInvoker.invoke(exchange, o);
			}

		});
		sFactoryBean.setResourceClasses(EmployeeResource.class);
		sFactoryBean.setProviders(Arrays.asList(new JacksonJsonProvider()));
		sFactoryBean.setResourceProvider(EmployeeResource.class,
				new SingletonResourceProvider(new EmployeeResource(), true));
		sFactoryBean.setAddress(BASE_ADDRESS);
		server = sFactoryBean.create();
	}

	public static void main(String[] args) throws Exception {
		try {
			startServer();
			WebClient client = WebClient.create(BASE_ADDRESS).path("/employees/12");
			Employee employee = client.get(Employee.class);
			System.out.println(employee);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.stop();
			server.destroy();
		}
	}

	private static void runStandaloneClient() {
		/*
		 * List<Object> providers = new ArrayList<Object>(); providers.add( new
		 * JacksonJaxbJsonProvider() );
		 * 
		 * WebClient client =
		 * WebClient.create("http://localhost:8080/cxf-sandbox/cxf-servlet",
		 * providers); client =
		 * client.accept("application/json").type("application/json").path(
		 * "/employees/Abhijith").query("id", "1");
		 * 
		 * Employee employee = client.get(Employee.class);
		 * System.out.println("Employee:" + employee.getName());
		 */
	}
}
