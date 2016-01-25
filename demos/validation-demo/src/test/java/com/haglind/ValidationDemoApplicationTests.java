package com.haglind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ValidationDemoApplication.class)
public class ValidationDemoApplicationTests {

	@Autowired
	RegistrationServiceBean bean;

	@Test
	public void validRegistration() {
		User user = new User("Karl", "Karlsson", "kalle@kalle.com", tomorrow());
		bean.register(user);
	}

	@Test
	public void nullFirstName() {
		User user = new User(null, "Karlsson", "kalle@kalle.com", tomorrow());
		try {
			bean.register(user);
		} catch (IllegalArgumentException ex) {
			assertEquals("firstName: You need to enter a firstname!", ex.getMessage());
		}
	}

	@Test
	public void invalidEmail() {
		try {
			User user = new User("Kalle", "Karlsson", "kalle@com", tomorrow());
			bean.register(user);
		} catch (IllegalArgumentException ex) {
			assertEquals("Not a valid email!", ex.getMessage());
		}
	}

	@Test
	public void expiresNotFuture() {
		try {
			User user = new User("Karl", "Karlsson", "kalle@kalle.com", new Date());
			bean.register(user);
			fail();
		} catch (IllegalArgumentException ex) {
		}
	}
	
	private Date tomorrow() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

}
