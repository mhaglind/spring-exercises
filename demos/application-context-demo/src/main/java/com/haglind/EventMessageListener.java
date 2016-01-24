package com.haglind;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventMessageListener implements ApplicationListener<MessageEvent> {

	long numberOfEvents;

	@Override
	public void onApplicationEvent(MessageEvent event) {
		System.out.println(event);
		numberOfEvents++;
	}

	@EventListener({ ContextStartedEvent.class, ContextRefreshedEvent.class, ContextStoppedEvent.class,
			ContextClosedEvent.class })
	public void genericEvents(ApplicationEvent event) {
		System.out.println(event);
	}

}
