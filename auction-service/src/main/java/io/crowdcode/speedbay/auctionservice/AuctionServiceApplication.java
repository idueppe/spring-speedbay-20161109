package io.crowdcode.speedbay.auctionservice;

import io.crowdcode.speedbay.auction.config.ApplicationLogConfiguration;
import io.crowdcode.speedbay.auction.config.JdbcTransactionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by idueppe on 22.06.17.
 */
@SpringBootApplication
@Import({ApplicationLogConfiguration.class, JdbcTransactionConfiguration.class})
public class AuctionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionServiceApplication.class, args);
    }

}
