package com.haglind.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.haglind.client")
public class EurekaDemoClientApplication {


    public static void main(String[] args) {
        SpringApplication.run(EurekaDemoClientApplication.class, "--spring.profiles.active=client");
    }

}
