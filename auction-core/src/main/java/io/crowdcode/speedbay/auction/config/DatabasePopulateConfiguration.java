package io.crowdcode.speedbay.auction.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Configuration
@Import(DataSourceConfiguration.class)
public class DatabasePopulateConfiguration {

    @Value("classpath:applog_schema_h2.sql")
    private Resource sqlScriptResource;

    @Bean
    public DatabasePopulator databasePopulator(DataSource dataSource) {
        log.info(" Populating Database ");

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setContinueOnError(true);
        populator.addScript(sqlScriptResource);
        try {
            populator.populate(dataSource.getConnection());
        } catch (SQLException e) {
            log.error("Exception Populating Database", e);
        }
        return populator;
    }

}