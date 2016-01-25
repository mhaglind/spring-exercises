package com.haglind;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	
	@NotNull(message="com.haglind.constraints.user.firstname.null")
	@Size(max=20)
	private final String firstName;
	
	@NotNull
	@Size(max=80)
	private final String lastName;
	
	@NotNull
	@Future
	private final Date expires;
	
	@NotNull
	@ValidEmail(message="com.haglind.constraints.user.email")
	private final String emailAddress;

	public User(String firstName, String lastName, String emailAddress, Date expires) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.expires = expires;
	}
	
}
