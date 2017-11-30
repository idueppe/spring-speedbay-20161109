package io.crowdcode.speedbay.auction.repository;

import io.crowdcode.speedbay.auction.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ApplicationLogRepositoryBean extends NamedParameterJdbcDaoSupport implements ApplicationLogRepository {
    public static final String INSERT_SQL = "INSERT INTO Application_Log (id, message, createdAt, createdBy)"
            + " VALUES (nextVal('AppLogSequence'), :message, :createdAt, :createdBy)";
    public static final String SELECT_BETWEEN_SQL = "SELECT id, message, createdAt, createdBy "
            + " FROM Application_Log WHERE createdAt BETWEEN :from and :to";

    /*
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void init() {
        setDataSource(dataSource);
    }
     */

    /* Com Spring Team favorisierte Variante */
    @Autowired
    public ApplicationLogRepositoryBean(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public void log(String message, LocalDateTime createdAt, String createdBy) {
        getNamedParameterJdbcTemplate().update(INSERT_SQL,
                new MapSqlParameterSource()
                        .addValue("message", message)
                        .addValue("createdAt", Timestamp.valueOf(createdAt))
                        .addValue("createdBy", createdBy));
    }

    @Override
    // RowMapper als Lambda Ausdruck
    public List<Message> findLogs(LocalDateTime from, LocalDateTime to) {
        return getNamedParameterJdbcTemplate().query(SELECT_BETWEEN_SQL,
                new MapSqlParameterSource()
                        .addValue("from", Timestamp.valueOf(from))
                        .addValue("to", Timestamp.valueOf(to)),
                (rs, rowNum) -> new Message()
                        .setMessage(rs.getString("message"))
                        .setCreatedBy(rs.getString("createdBy"))
                        .setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime()));
//        return getNamedParameterJdbcTemplate().query(SELECT_BETWEEN_SQL,
//                new MapSqlParameterSource()
//                        .addValue("from", Timestamp.valueOf(from))
//                        .addValue("to", Timestamp.valueOf(to)),
//                new MessageRowMapper());

    }

    private static class MessageRowMapper implements RowMapper<Message> {

        @Override
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Message()
                    .setMessage(rs.getString("message"))
                    .setCreatedBy(rs.getString("createdBy"))
                    .setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        }
    }
}
