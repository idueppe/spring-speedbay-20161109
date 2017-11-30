package io.crowdcode.speedbay.auction.application;

import io.crowdcode.speedbay.auction.config.ApplicationLogConfiguration;
import io.crowdcode.speedbay.auction.config.DatabasePopulateConfiguration;
import io.crowdcode.speedbay.auction.config.JdbcTransactionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        ApplicationLogConfiguration.class,
        DatabasePopulateConfiguration.class,
        JdbcTransactionConfiguration.class})
public class AuctionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionServiceApplication.class, args);
    }
}
