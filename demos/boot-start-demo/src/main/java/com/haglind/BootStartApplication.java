package com.haglind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableAutoConfiguration
@Controller
public class BootStartApplication {

	@RequestMapping(value = "/")
	public @ResponseBody String helloWorld() {
		return "Hello world of Spring Boot!";
	}

	public static void main(String[] args) {
		SpringApplication.run(BootStartApplication.class, args);
	}

}
