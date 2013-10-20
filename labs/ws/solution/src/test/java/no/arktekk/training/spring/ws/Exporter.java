package no.arktekk.training.spring.ws;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exporter {

	public static void main(String[] args) throws Exception {
		
		// http://localhost:8088/AuctionWebService?wsdl
		
		ClassPathXmlApplicationContext context 
			= new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
	}

}
