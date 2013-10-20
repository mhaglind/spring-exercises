package no.arktekk.training.spring.auction;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import no.arktekk.training.spring.domain.Auction;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.oxm.Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JmsTest {

	@Autowired
	private ConnectionFactory connectionFactory;

	@Autowired
	private Destination queue;

	@Autowired
	private Marshaller jaxbMarshaller;

	// @Autowired
	// private MessageConverter messageConverter;

	@Test
	public void jmsBootstrap() throws Exception {
		System.out.println("jmsBootstrap");
		JmsTemplate jms = new JmsTemplate(connectionFactory);
		jms.setDefaultDestination(queue);
		jms.setMessageConverter(new MarshallingMessageConverter(jaxbMarshaller));

		jms.convertAndSend(new Auction("42", 10d, "descreiption", new DateTime(),
				new DateTime(), null));
		
		Thread.sleep(5000);
	}
}
