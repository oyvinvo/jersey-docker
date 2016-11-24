package no.volden.hello;

import no.volden.Main;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest {

	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() {
		server = Main.startServer();
		Client c = ClientBuilder.newClient();
		target = c.target(Main.BASE_URI);
	}

	@After
	public void tearDown() {
		server.shutdownNow();
	}

	@Test
	public void hiya() {
		String response = target.path("HelloWorld").request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals("Hi world!", response);
	}

	@Test
	public void hiyaJason() {
		HiyaJson response = target.path("HelloWorld").request(MediaType.APPLICATION_JSON).get(HiyaJson.class);
		assertEquals("Hi world!", response.getLabel());

	}

}