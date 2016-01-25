package com.haglind;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceBean {

	@Autowired
	private Validator validator;

	@Autowired
	private MessageSource messages;

	public void register(User newUser) {
		Set<ConstraintViolation<User>> violations = validator.validate(newUser);
		if (violations.size() > 0) {
			for (ConstraintViolation<User> violation : violations) {
				System.out.println("Validation violation found: " + violation);
				throw new IllegalArgumentException(messages.getMessage(violation.getMessageTemplate(),
						new Object[] { violation.getPropertyPath().toString() }, Locale.ENGLISH));
			}
		}
	}
}
