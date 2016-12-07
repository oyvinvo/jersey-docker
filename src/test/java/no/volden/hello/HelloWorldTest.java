package no.volden.hello;

import no.volden.Main;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("HelloWorld services should greet in both text and json")
class HelloWorldTest {

	private static HttpServer server;
	private static WebTarget target;

	@BeforeAll
	static void initAll() {
		server = Main.startServer();
		Client c = ClientBuilder.newClient();
		target = c.target(Main.BASE_URI);
	}

	@AfterAll
	static void tearDownAll() {
		server.shutdownNow();
	}

	@Test
	@DisplayName("I want to be greeted in plain text")
	@Tag("text_respons")
	void hiya() {
		String response = target.path("HelloWorld").request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals("Hi world! Greetings from: unknown", response);
	}

	@Test
	@DisplayName("I want to be greeted in json")
	@Tag("json_respons")
	void hiyaJason() {
		HiyaJson response = target.path("HelloWorld").request(MediaType.APPLICATION_JSON).get(HiyaJson.class);
		assertEquals("Hi world!", response.getLabel());

	}

}