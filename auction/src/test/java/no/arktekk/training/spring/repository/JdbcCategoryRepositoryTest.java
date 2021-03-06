package no.arktekk.training.spring.repository;

import no.arktekk.training.spring.config.DatabaseConfig;
import no.arktekk.training.spring.config.TestDataPopulator;
import no.arktekk.training.spring.domain.Category;
import no.arktekk.training.spring.repository.impl.JdbcCategoryRepository;
import no.arktekk.training.spring.util.Cache;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class JdbcCategoryRepositoryTest {
    private DatabaseConfig config = new DatabaseConfig();

    @Before
    public void testAssumtions() throws Exception {
        DataSource dataSource = config.dataSource();
        JdbcTemplate template = new JdbcTemplate(dataSource);
        new TestDataPopulator(dataSource).generateDatabaseIfEmpty();
        assertEquals(7, template.queryForInt("select count(*) from Categories"));
    }

    @Test
    public void findExistingCategory() throws Exception {
        BasicCrudRepository<Category> repository = new JdbcCategoryRepository(config.dataSource());
        Category category = repository.find(1);
        assertEquals("Progrock", category.getName());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findNonExistingCategory() throws Exception {
        BasicCrudRepository<Category> repository = new JdbcCategoryRepository(config.dataSource());
        repository.find(MAX_VALUE);
    }

    @Test
    public void updateExistingCategory() throws Exception {
        BasicCrudRepository<Category> repository = new JdbcCategoryRepository(config.dataSource());
        Category category = repository.find(1);
        assertEquals("Progrock", category.getName());
        Category updatedCategory = new Category(1, "Flott musikk");
        repository.update(updatedCategory);
        assertEquals("Flott musikk", repository.find(1).getName());
        repository.update(category);
        assertEquals("Progrock", repository.find(1).getName());
    }

    @Test
    public void listAllCategories() throws Exception {
        BasicCrudRepository<Category> repository = new JdbcCategoryRepository(config.dataSource());
        List<Category> categories = repository.list();
        assertEquals(7, categories.size());
    }

    @Test
    public void addAndRemoveCategory() throws Exception {
        BasicCrudRepository<Category> repository = new JdbcCategoryRepository(config.dataSource());
        int id = MAX_VALUE;
        Category category = new Category(id, "Sutrepop");
        repository.addNew(category);
        assertEquals(8, repository.list().size());
        repository.delete(category);
        assertEquals(7, repository.list().size());
    }

    @Test
    public void cache() throws Exception {
        Cache<Category> cache = new Cache<Category>();
        BasicCrudRepository<Category> repository = new JdbcCategoryRepository(config.dataSource(), cache);
        assertEquals(0, cache.cacheHits());
        assertEquals(0, cache.cacheMiss());
        Category category = repository.find(1);
        assertEquals("Progrock", category.getName());
        assertEquals(0, cache.cacheHits());
        assertEquals(1, cache.cacheMiss());
        repository.find(1);
        assertEquals(1, cache.cacheHits());
        assertEquals(1, cache.cacheMiss());
    }

    @After
    public void checkSideEffects() throws Exception {
        JdbcTemplate template = new JdbcTemplate(config.dataSource());
        assertEquals(7, template.queryForInt("select count(*) from Categories"));
    }
}

