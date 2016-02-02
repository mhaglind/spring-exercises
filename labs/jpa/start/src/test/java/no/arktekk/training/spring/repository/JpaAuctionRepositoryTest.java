package no.arktekk.training.spring.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import no.arktekk.training.spring.domain.Auction;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = no.arktekk.training.spring.config.AppConfig.class)
public class JpaAuctionRepositoryTest {

	@Autowired
	private AuctionRepository auctionRepository;

	/**
	 * Inspect the configuration-file: AppConfig.java  (No changes needed)
	 * 
	 * In class Auction
	 * 	- Add JPA mappings to make this an entity, annotate the id field as a GeneratedValue
	 *
	 * In interface AuctionRepository
	 *  - Make this a Spring Data Repository by extending CrudRepository
	 *  
	 * In testmethod below - replace nulls with commented out method calls.
	 * 
	 * @throws Exception
	 */
	@Test
	public void step_1() throws Exception {
		
		Auction auction = null; // auctionRepository.findOne(1L);
		assertEquals("Mint prog rock albums", auction.getDescription());
		
		List<Auction> auctions = null; //auctionRepository.findAll();
		assertEquals(3, auctions.size());
		
	}

	/**
	 * Bonus: 
	 * 	Add a method to AuctionRepository that queries for auctions
	 * with a minimum price lower than x.
	 * 	Test by adding call to new method in testcase below.
	 *
	 * @throws Exception
	 */
	@Test
	public void bonus() throws Exception {
		
		//auctionRepository.saveAndFlush(new Auction(8,"Cheap record"));
		List<Auction> cheap = null;  // auctionRepository.findByMinimumPriceLessThan(15);
		assertEquals("Cheap record", cheap.get(0).getDescription());
		
	}

}
