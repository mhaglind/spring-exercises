package com.haglind;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpaDemoApplication.class)
public class JpaDemoApplicationTests {

    @Autowired
    UserRepository userRepo;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSave() {
        userRepo.saveAndFlush(new User());
        assertEquals(1, userRepo.findAll().size());
    }
}
