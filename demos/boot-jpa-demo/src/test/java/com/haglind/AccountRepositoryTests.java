package com.haglind;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountRepositoryTests {

	@Autowired
	AccountRepository repo;

	@Test
	@Transactional
	public void save() {
		Account saved = repo.save(new Account("milliwig22", 100445L, new Date()));
		assertNotNull(saved.id);
		
		List<Account> all = repo.findAll();
		assertEquals(1,all.size());
	}

}
