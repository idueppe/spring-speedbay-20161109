package io.crowdcode.speedbay.auction.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataSourceConfiguration.class)
@TestPropertySource(properties = "stage=h2")
public class DataSourceConfigurationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSource() throws Exception {
        Connection connection = dataSource.getConnection();
        assertNotNull(connection);
        connection.close();
        assertNotNull(dataSource);
    }
}