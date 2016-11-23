package no.volden.hello;

import no.volden.Main;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class HelloWorldTest {

	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() {
		server = Main.startServer();
		Client c = ClientBuilder.newClient();

		// uncomment the following line if you want to enable
		// support for JSON in the client (you also have to uncomment
		// dependency on jersey-media-json module in pom.xml and Main.startServer())
		// --
		// c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

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