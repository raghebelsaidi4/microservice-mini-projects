package com.ragheb.ticket.booking.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ComServicesRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComServicesRegistryApplication.class, args);
	}

}
