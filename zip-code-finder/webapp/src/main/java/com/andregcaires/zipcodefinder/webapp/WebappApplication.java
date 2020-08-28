package com.andregcaires.zipcodefinder.webapp;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan(basePackages = {"com.andregcaires.zipcodefinder.core", 
		"com.andregcaires.zipcodefinder.webapp",
		"com.andregcaires.zipcodefinder.webapp.configurations"})
public class WebappApplication implements CommandLineRunner {

	@Autowired
	private Environment environment;

	private final String SWAGGER_ENDPOINT = "/swagger-ui.html";
	private final String ACTUATOR_ENDPOINT = "/actuator";
	private final String HTTP_PREFIX = "http://";

	Logger logger = LoggerFactory.getLogger(WebappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		showLogMessage();
	}
	
	public void showLogMessage() throws UnknownHostException {
		
		StringBuilder logMessage = new StringBuilder();

		logMessage.append(HTTP_PREFIX);
		logMessage.append(InetAddress.getLocalHost().getHostAddress());
		logMessage.append(":");
		logMessage.append(environment.getProperty("server.port"));

		logger.info("API documentation URI: " + logMessage.toString() + SWAGGER_ENDPOINT);
		logger.info("Health check info URI: " + logMessage.toString() + ACTUATOR_ENDPOINT);
	}

}
