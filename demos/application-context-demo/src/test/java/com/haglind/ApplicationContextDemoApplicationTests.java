package com.haglind;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationContextDemoApplication.class)
public class ApplicationContextDemoApplicationTests {

	@Autowired
	LocalMessageServiceBean bean;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testMessageService() {

		assertEquals("Hello", bean.getMessage("general.greeting"));
		assertEquals("A problem of type i/o has occured in module system-core!",
				bean.getMessage("general.problem", "i/o", "system-core"));

		bean.locale = new Locale("se");

		assertEquals("Hej", bean.getMessage("general.greeting"));
		assertEquals("Ett problem av typen i/o har uppstått i modulen system-core!",
				bean.getMessage("general.problem", "i/o", "system-core"));

	}
}
