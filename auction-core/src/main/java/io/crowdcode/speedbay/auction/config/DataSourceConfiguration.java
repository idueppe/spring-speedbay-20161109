package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.common.AnsiColor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Slf4j
@Configuration
@PropertySource(name = "irgend-ein-name", value = "classpath:${stage:local}-database.properties")
public class DataSourceConfiguration {

    @Value("${jdbcDriverClassName}")
    private String jdbcDriverClassName;
    @Value("${jdbcUrl}")
    private String jdbcUrl;
    @Value("${jdbcUsername}")
    private String jdbcUsername;
    @Value("${jdbcPassword}")
    private String jdbcPassword;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setTrimValues(true);
        return configurer;
    }

    @Bean
    public DataSource dataSource() {
        log.info(AnsiColor.blue("init datasource {}"), jdbcUrl);

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbcDriverClassName);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);

        return dataSource;
    }


}
