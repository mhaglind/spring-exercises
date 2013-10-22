package no.arktekk.training.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.repository.AuctionRepository;
import no.arktekk.training.spring.repository.impl.KeyedAuctionRepository;
import no.arktekk.training.spring.repository.impl.StubAuctionRepository;
import no.arktekk.training.spring.service.AuctionService;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * @author <a href="mailto:marten@haglind.com">Marten Haglind</a>
 */
public class ProfilesTest {

	/**
	 * Complete todos in createSpringContainer method below to make this test
	 * run.
	 */
	@Test
	public void step_1() {
		ApplicationContext ctx = createSpringContainer();
		assertNotNull(ctx);
		AuctionService auctionService = lookupAuctionService(ctx);
		assertNotNull(auctionService);
	}

	/**
	 * Create a new configuration file called repository-test-ctx.xml. In this
	 * file create a bean definition for StubAuctionRepository, as per previous
	 * exercises.
	 */
	@Test
	public void step_2() {
		ApplicationContext ctx = createSpringContainer();
		assertNotNull(ctx);
		AuctionRepository auctionRepository = lookupAuctionRepository(ctx);
		assertNotNull(auctionRepository);
	}

	/**
	 * Set a profile on the beans in repository-text-ctx.xml. Call it 'test'.
	 * Also complete todos in createSpringContainer(String profile) below to
	 * make this work.
	 */
	@Test
	public void step_3() {
		ApplicationContext ctx = createSpringContainer("test");
		assertNotNull(ctx);
		AuctionRepository auctionRepository = lookupAuctionRepository(ctx);
		assertNotNull(auctionRepository);
		assertEquals(StubAuctionRepository.class, auctionRepository.getClass());
	}

	/**
	 * Configure an additional version of the repository: - Add a new
	 * configuration file: repository-prod.ctx.xml - In the new file add a bean
	 * definition of KeyedAuctionRepository. Give it the name
	 * 'auctionRepository'. Note that the bean has a property 'initialCapacity'
	 * that needs to be set.
	 */
	@Test
	public void step_4() {
		ApplicationContext ctx = createSpringContainer();
		assertNotNull(ctx);
		AuctionRepository auctionRepository = lookupAuctionRepository(ctx);
		assertNotNull(auctionRepository);
		assertEquals(KeyedAuctionRepository.class, auctionRepository.getClass());
	}

	/**
	 * Verify complete application chain by running this test with profile
	 * 'test' No todos. Just observe and understand behaviour.
	 */
	@Test
	public void step_5() {
		AuctionService auctionService = testAuctionService("test");
		assertEquals("Test auction", auctionService.findById(0D).description());
	}

	/**
	 * Verify complete application chain by running this test with profile
	 * 'prod' No todos. Just observe and understand behaviour.
	 */
	@Test
	public void step_6() {
		AuctionService auctionService = testAuctionService("prod");
		try {
			auctionService.findById(0D);
			fail();
		} catch (IllegalArgumentException ex) {
			assertEquals("Auction Id not found", ex.getMessage());
		}
	}

	/*
	 * Helper. Used from step5 and step6.
	 */
	private AuctionService testAuctionService(String env) {
		ApplicationContext ctx = createSpringContainer(env);
		AuctionRepository auctionRepository = lookupAuctionRepository(ctx);
		AuctionService auctionService = lookupAuctionService(ctx);
		assertEquals(0, auctionService.allRunningAuctions().size());
		auctionRepository.createNewAuction(new Auction(1, "My first auction"));
		auctionRepository.createNewAuction(new Auction(2, "My second auction"));
		assertEquals(2, auctionService.allRunningAuctions().size());
		Auction auction = auctionService.findById(1D);
		assertEquals("My first auction", auction.description());
		return auctionService;
	}

	private ApplicationContext createSpringContainer() {
		// TODO: Create and return an application context,
		// load all files called xxx-ctx.cml from classpath.
		// Hint: "classpath:/*-ctx.xml"
		return null;
	}

	private ApplicationContext createSpringContainer(String profile) {
		// TODO: Create and return an app context as above
		// Set the active profile on the context to the profile name
		// given as a method parameter.
		return null;
	}

	private AuctionRepository lookupAuctionRepository(ApplicationContext ctx) {
		return ctx.getBean("auctionRepository", AuctionRepository.class);
	}

	private AuctionService lookupAuctionService(ApplicationContext ctx) {
		return ctx.getBean("auctionService", AuctionService.class);
	}
}
