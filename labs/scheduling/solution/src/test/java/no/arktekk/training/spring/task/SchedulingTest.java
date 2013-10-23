package no.arktekk.training.spring.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import no.arktekk.training.spring.task.AuctionCounter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SchedulingTest {

	@Autowired
	AuctionCounter auctionCounter;

	@Autowired
	ApplicationContext ctx;

	/**
	 * 1. Define the worker.
	 * 
	 * - Create a new class called
	 * no.arktekk.training.spring.task.AuctionCounter.
	 * 
	 * - Create a method called countAuctions() in this class that calls
	 * AuctionRepository.listAllRunningAuctions method and prints to system.out
	 * the number of auctions returned. Hint: dependency inject with autowiring
	 * an AuctionRepository for use.
	 * 
	 * - Annotate the class with @Component or make it a Spring Bean in some
	 * other way
	 */
	@Test
	public void step1() {
		try {
			auctionCounter.countAuctions();
		} catch (Exception ex) {
			fail("Not a really good test, but at least verifies not exceptions are thrown.");
		}
	}

	/**
	 * 2. Configure scheduling support.
	 * 
	 * In applicationContext.xml add: <task:annotation-driven />
	 */
	@Test
	public void step2() {
		assertNotNull(ctx.getBean(ScheduledAnnotationBeanPostProcessor.class));
	}

	/**
	 * 3. Define the trigger.
	 * 
	 * On the method created in step one - annotate with @Scheduled. Make the
	 * trigger fire every 10 seconds.
	 */
	@Test
	public void step3() throws Exception {
		Scheduled scheduled = auctionCounter.getClass()
				.getMethod("countAuctions", (Class<?>[]) null)
				.getAnnotation(Scheduled.class);
		assertEquals(10000, scheduled.fixedRate());
	}

	/**
	 * 4. Run application.
	 * 
	 * Run web application by 'Run As:Java Application' on AuctionApp.
	 * 
	 * Observe loggings every 10 seconds in console windows coming from task.
	 * Note that http://localhost:8080/index.html has a small form for adding
	 * new auctions if you want the counted number to change.
	 * 
	 * Comment out the fail statement below when you are satisfied it works. :)
	 */
	@Test
	public void step4() {
		// fail();
	}
}
