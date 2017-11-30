package io.crowdcode.speedbay.auction.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Configuration
@Import(DataSourceConfiguration.class)
public class DatabasePopulateConfiguration {
    private static final String APPLICATIONLOG_SQL = "applog_schema_h2.sql";

    @Bean
    public DatabasePopulator databasePopulator(DataSource dataSource) {
        log.info(" Populating Database ");

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setContinueOnError(true);
        populator.addScript(new ClassPathResource(APPLICATIONLOG_SQL));
        try {
            populator.populate(dataSource.getConnection());
        } catch (SQLException e) {
            log.error("Exception Populating Database", e);
        }
        return populator;
    }
}
