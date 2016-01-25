package com.haglind;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	private Pattern pattern;

	@Override
	public void initialize(ValidEmail constraintAnnotation) {
		String regex = "\\A[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
				+ "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\z";
		pattern = Pattern.compile(regex);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return pattern.matcher(value).matches();
	}

}
