package no.volden.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("HelloWorld")
public class HelloWorld {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hiya()  {
		String hostnameOrNull = System.getenv("HOSTNAME");
		String hostname = null != hostnameOrNull ? hostnameOrNull : "unknown";
		return "Hi world! Greetings from: ".concat(hostname);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HiyaJson hiyaJason() {
		return new HiyaJson("Hi world!");
	}

}
