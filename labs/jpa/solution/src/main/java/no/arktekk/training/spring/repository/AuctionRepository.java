package no.arktekk.training.spring.repository;

import no.arktekk.training.spring.domain.Auction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author <a href="mailto:marten@haglind.com">Mårten Haglind</a>
 */
public interface AuctionRepository extends JpaRepository<Auction, Long> {
	public List<Auction> findByMinimumPriceLessThan(double below);
}
