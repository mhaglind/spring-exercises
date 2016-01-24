package com.haglind;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class ApplicationContextDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationContextDemoApplication.class, args);
	}

	@Bean
	Locale defaultLocale() {
		return Locale.ENGLISH;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

}
