package no.arktekk.training.spring.jms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmsServer {

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath:server/applicationContext.xml");
	}

}
