package no.arktekk.training.spring.repository.impl;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.mapper.AuctionMapper;
import no.arktekk.training.spring.repository.AuctionRepository;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.List;

import static no.arktekk.training.spring.util.DatabaseUtils.no_NO;
import static no.arktekk.training.spring.util.DatabaseUtils.timeStampFormatter;


/**
 * @author <a href="mailto:marten@haglind.com">Marten Haglind</a>
 */
@Repository
public class JdbcAuctionRepository implements AuctionRepository {
    private final JdbcTemplate template;

    @Autowired
    public JdbcAuctionRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public List<Auction> listAllRunningAuctions() {
        DateTime now = new DateTime();
        return template.query(
                "select * from Auctions where ? between starts and expires",
                new AuctionMapper(),
                timeStampFormatter.print(now.toDate(), no_NO));
    }

    public Auction findById(String auctionId) {
        return template.queryForObject("select * from Auctions where id = ?", new AuctionMapper(), auctionId);
    }

    public void newAuction(Auction auction) {
        // Simulate slow database writes
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        template.update("insert into Auctions values(?,?,?,?,?)",
                auction.id(),
                auction.minimumPrice(),
                timeStampFormatter.print(auction.starts().toDate(), no_NO),
                timeStampFormatter.print(auction.expires().toDate(), no_NO),
                auction.description());
    }
}
