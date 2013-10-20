package no.arktekk.training.spring.batchclient;

import no.arktekk.training.spring.domain.Auction;

import org.joda.time.DateTime;
import org.springframework.jms.core.JmsTemplate;

public class AuctionCreator {

	JmsTemplate jmsTemplateForSending;

	public void sendAuctionMessage(String description, double price) {
		DateTime now = new DateTime();
		jmsTemplateForSending.convertAndSend(new Auction(null, price,
				description, now.plusDays(-1), now.plusDays(7), null));
	}

	public void setJmsTemplateForSending(JmsTemplate jmsTemplateForSending) {
		this.jmsTemplateForSending = jmsTemplateForSending;
	}
}
