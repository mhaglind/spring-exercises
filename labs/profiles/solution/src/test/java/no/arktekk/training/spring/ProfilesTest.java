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
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author <a href="mailto:marten@haglind.com">Mårten Haglind</a>
 */
public class ProfilesTest {

    /**
     * Configure neccesary bean in applicationContext.xml and complete the
     * helper methods in order to get this test to run.
     * <p/>
     * TIP: The repository implementation is:
     * no.arktekk.training.spring.repository.impl.StubAuctionRepository
     */
    @Test
    public void step_1a() {
        ApplicationContext ctx = createSpringContainer();
        assertNotNull(ctx);
        AuctionRepository auctionRepository = lookupAuctionRepository(ctx);
        assertNotNull(auctionRepository);
        assertEquals(KeyedAuctionRepository.class, auctionRepository.getClass());
    }

    @Test
    public void step_1b() {
        ApplicationContext ctx = createSpringContainer("test");
        assertNotNull(ctx);
        AuctionRepository auctionRepository = lookupAuctionRepository(ctx);
        assertNotNull(auctionRepository);
        assertEquals(StubAuctionRepository.class, auctionRepository.getClass());
    }

    /**
     * Configure neccesary beans in applicationContext.xml and complete the
     * helper methods in order to get this test to run.
     * <p/>
     * TIP: The repository implementation is:
     * no.arktekk.training.spring.service.impl.DefaultAuctionService
     * TIP: The service needs the repository in order to work.
     */
    @Test
    public void step_2() {
        ApplicationContext ctx = createSpringContainer();
        AuctionService auctionService = lookupAuctionService(ctx);
        assertNotNull(auctionService);
    }

    /**
      * Complete TODO's
      * Replace all calls to fail() with proper test code
      */
    @Test
    public void step_3a() {
        ApplicationContext ctx = createSpringContainer();
        AuctionRepository auctionRepository = lookupAuctionRepository(ctx);
        AuctionService auctionService = lookupAuctionService(ctx);

        assertEquals(0, auctionService.allRunningAuctions().size());

        auctionRepository.createNewAuction(new Auction(1, "My first auction"));
        auctionRepository.createNewAuction(new Auction(2, "My second auction"));

        assertEquals(2, auctionService.allRunningAuctions().size());

        Auction auction = auctionService.findById(1D);
        assertEquals("My first auction", auction.description());

        try {
            auctionService.findById(0D);
            fail();
        } catch (IllegalArgumentException ex) {
            assertEquals("Auction Id not found", ex.getMessage());
        }
    }

    @Test
    public void step_3b() {
        ApplicationContext ctx = createSpringContainer("test");
        AuctionRepository auctionRepository = lookupAuctionRepository(ctx);
        AuctionService auctionService = lookupAuctionService(ctx);

        assertEquals(0, auctionService.allRunningAuctions().size());

        auctionRepository.createNewAuction(new Auction(1, "My first auction"));
        auctionRepository.createNewAuction(new Auction(2, "My second auction"));

        assertEquals(2, auctionService.allRunningAuctions().size());

        Auction auction = auctionService.findById(1D);
        assertEquals("My first auction", auction.description());

        assertEquals("Test auction", auctionService.findById(0D).description());
    }

    private ApplicationContext createSpringContainer() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:/*-applicationContext.xml");
        ctx.refresh();
        return ctx;
    }

    private ApplicationContext createSpringContainer(String profile) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles(profile);
        ctx.load("classpath:/*-applicationContext.xml");
        ctx.refresh();
        return ctx;
    }

    private AuctionRepository lookupAuctionRepository(ApplicationContext ctx) {
        return ctx.getBean("auctionRepository", AuctionRepository.class);
    }

    private AuctionService lookupAuctionService(ApplicationContext ctx) {
        return ctx.getBean("auctionService", AuctionService.class);
    }
}
