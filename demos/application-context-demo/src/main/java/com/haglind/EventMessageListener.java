package com.haglind;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EventMessageListener implements ApplicationListener<MessageEvent> {

	long numberOfEvents;
	
	@Override
	public void onApplicationEvent(MessageEvent event) {
		System.out.println(event);
		numberOfEvents++;
	}

}
