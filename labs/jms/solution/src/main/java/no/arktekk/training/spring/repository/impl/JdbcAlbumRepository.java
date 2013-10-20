package no.arktekk.training.spring.repository.impl;

import java.util.List;

import javax.sql.DataSource;

import no.arktekk.training.spring.domain.Album;
import no.arktekk.training.spring.domain.Category;
import no.arktekk.training.spring.domain.Label;
import no.arktekk.training.spring.mapper.AlbumMapper;
import no.arktekk.training.spring.repository.AlbumRepository;
import no.arktekk.training.spring.repository.BasicCrudRepository;
import no.arktekk.training.spring.util.RandomSleep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Repository
public class JdbcAlbumRepository implements AlbumRepository {
	private final JdbcTemplate template;
	private final AlbumMapper albumMapper;

	@Autowired
	public JdbcAlbumRepository(
			DataSource dataSource,
			@Qualifier("jdbcCategoryRepository") BasicCrudRepository<Category> categoryRepository,
			@Qualifier("jdbcLabelRepository") BasicCrudRepository<Label> labelRepository) {
		this.template = new JdbcTemplate(dataSource);
		albumMapper = new AlbumMapper(categoryRepository, labelRepository);
	}

	public Album findById(String id) {
		return template.queryForObject("select * from Albums WHERE id = ?",
				albumMapper, id);
	}

	public List<Album> listForAuction(String auctionId) {
		return template.query("select * from Albums where auctionId = ?",
				albumMapper, auctionId);
	}

	public void storeForAuction(String auctionId, List<Album> albums) {
        // Simulate slow database writes
    	RandomSleep.delayMaxSeconds(1);
    	
		for (Album album : albums) {
			album.assignNewId();
			template.update("insert into Albums values(?,?,?,?,?,?)", album
					.id(), auctionId, album.title(), album.artist(), album
					.category().getId(), album.label().getId());
		}
	}
}
