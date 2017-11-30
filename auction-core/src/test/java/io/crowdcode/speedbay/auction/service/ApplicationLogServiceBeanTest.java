package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.config.ApplicationLogConfiguration;
import io.crowdcode.speedbay.auction.config.JdbcTransactionConfiguration;
import io.crowdcode.speedbay.auction.model.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationLogConfiguration.class, JdbcTransactionConfiguration.class})
@TestPropertySource(properties = "stage=h2")
public class ApplicationLogServiceBeanTest {

    @Autowired
    private ApplicationLogService appLogService;

    @Test
    // SQL Statements vor dem Test laufen lassen
    @Sql(statements = "DELETE FROM Application_Log")
    public void testApplicationLogging() throws Exception {
        appLogService.log("JUNIT TEST %s", "LOG");
        List<Message> messages = appLogService
                .lastLogs(Duration.of(5, ChronoUnit.SECONDS));
        messages.forEach(System.out::println);
        assertThat(messages, contains(hasProperty("message", is("JUNIT TEST LOG"))));
    }

}