package no.volden.hello;

import no.volden.Main;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;


class HelloWorldTest {

	private static HttpServer server;
	private static WebTarget target;

	@BeforeAll
	public static void setUp() {
		server = Main.startServer();
		Client c = ClientBuilder.newClient();
		target = c.target(Main.BASE_URI);
	}

	@AfterAll
	static void tearDown() {
		server.shutdownNow();
	}

	@Test
	void hiya() {
		String response = target.path("HelloWorld").request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals("Hi world!", response);
	}

	@Test
	void hiyaJason() {
		HiyaJson response = target.path("HelloWorld").request(MediaType.APPLICATION_JSON).get(HiyaJson.class);
		assertEquals("Hi world!", response.getLabel());

	}

}