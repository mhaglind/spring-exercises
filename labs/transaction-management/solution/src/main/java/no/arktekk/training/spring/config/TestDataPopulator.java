package no.arktekk.training.spring.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Will by default, if registered as a bean in the container, create neccecary
 * tables, then populate them with a small set of test data.
 * <p/>
 * <p/>
 * To prevent it to populate test data, set the createTestData property to false
 *
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Component
public class TestDataPopulator {
    private final DataSource dataSource;
    private final JdbcTemplate template;

    @Autowired
    public TestDataPopulator(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void generateDatabaseIfEmpty() {
        if (databaseEmpty()) {
            createTables();
        }
    }

    private boolean databaseEmpty() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            return !connection.getMetaData().getTables(null, null, "AUCTIONS", null).next();
        } catch (SQLException e) {
            throw new RuntimeException("Could not get table metadata", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Do nothing :)
            }
        }

    }

    private void createTables() {
        template.update("CREATE TABLE Auctions(id DOUBLE PRIMARY KEY, minimumPrice DOUBLE, starts TIMESTAMP, expires TIMESTAMP, description VARCHAR)");
    }
}
