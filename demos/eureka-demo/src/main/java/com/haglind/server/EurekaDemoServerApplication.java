package com.haglind.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication(scanBasePackages = "com.haglind.server")
public class EurekaDemoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaDemoServerApplication.class, args);
	}

}
