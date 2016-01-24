package com.haglind;

import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private final String message;

	public MessageEvent(Object source, String message) {
		super(source);
		this.message = message;

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageEvent [message=").append(message).append(", event=").append(super.toString())
				.append("]");
		return builder.toString();
	}

}
