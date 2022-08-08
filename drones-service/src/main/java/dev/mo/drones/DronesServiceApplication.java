package dev.mo.drones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DronesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DronesServiceApplication.class, args);
	}

}
