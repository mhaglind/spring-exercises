package com.haglind;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class LocalMessageServiceBean {

	@Autowired
	ApplicationContext ctx;

	@Autowired
	Locale locale;

	public String getMessage(String key, Object... args) {
		MessageSource messages = (MessageSource) ctx;
		return messages.getMessage(key, args, locale);
	}
}
