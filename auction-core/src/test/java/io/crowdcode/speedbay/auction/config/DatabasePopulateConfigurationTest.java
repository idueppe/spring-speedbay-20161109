package io.crowdcode.speedbay.auction.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        JdbcTransactionConfiguration.class,
        DatabasePopulateConfiguration.class
})
public class DatabasePopulateConfigurationTest {

    private static final String INSERT_ONE =
            "INSERT INTO Application_Log " +
                    "(id, message, createdAt, createdBy) VALUES" +
                    " (nextVal('AppLogSequence'), :message, :createdAt, :createdBy)";
    private static final String SELECT_ONE = "SELECT message FROM Application_Log WHERE id=:id";
    private static final String SELECT_ALL = "SELECT message FROM Application_Log";

    @Autowired
    private DataSource dataSource;

    @Test
    @Repeat(3)
    public void testJdbcTemplate() throws Exception {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        jdbcTemplate.update(INSERT_ONE, new MapSqlParameterSource()
                .addValue("message", "Log Message 1")
                .addValue("createdBy", "USER")
                .addValue("createdAt", Timestamp.valueOf(LocalDateTime.now())));

        MapSqlParameterSource parameters = new MapSqlParameterSource("id", 1l);
        String result = jdbcTemplate.queryForObject(SELECT_ONE, parameters, String.class);
        assertThat(result, is("Log Message 1"));


        // Verwendung von RowMapper als Lambda-Ausdruck
        List<String> msgs = jdbcTemplate.query(SELECT_ALL, (rs, rowNum) -> {
            return rs.getString("message");
        });
        msgs.forEach(System.out::println);

        // Das selbe nochmal ohne RowMapper
        List<String> messages = jdbcTemplate.queryForList(SELECT_ALL, new MapSqlParameterSource(), String.class);
        messages.forEach(System.out::println);
    }

}