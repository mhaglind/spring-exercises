package com.haglind;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpaDemoApplication.class)
@Transactional
public class UserRepositoryTests {

	@Autowired
	UserRepository userRepo;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void testSave() {

		long count = userRepo.count();

		User user1 = userRepo.saveAndFlush(new User("Olav"));
		assertEquals(count + 1, userRepo.findAll().size());
		Long sid1 = user1.getSid();

		User user2 = userRepo.saveAndFlush(new User("Eva"));
		assertEquals(count + 2, userRepo.count());

		userRepo.delete(user2.getSid());
		assertEquals(count + 1, userRepo.count());

		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM USER WHERE NAME='Olav'");
		assertEquals(1, list.size());
		assertEquals(sid1, list.get(0).get("sid"));
	}

	@Test
	public void testFinder() {

		assertEquals(1, userRepo.findByName("Nils").size());
		assertEquals(2, userRepo.findByNameLike("Ni%").size());

	}
}