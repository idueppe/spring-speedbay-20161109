package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.model.Bid;
import io.crowdcode.speedbay.auction.model.DomainFactory;
import io.crowdcode.speedbay.auction.model.DomainFactoryBean;
import io.crowdcode.speedbay.common.time.TimeMachine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;
import java.util.List;

import static io.crowdcode.speedbay.common.AnsiColor.blue;
import static io.crowdcode.speedbay.common.AnsiColor.green;
import static io.crowdcode.speedbay.common.AnsiColor.red;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Slf4j
@Configuration
public class DomainFactoryConfiguration {

    @Bean
    public DomainFactory domainFactory() {
        log.info(red("Creating DomainFractoryBean"));
        return new DomainFactoryBean();
    }

    @Bean
    @Scope("prototype")
    public Auction auction(List<Bid> bids) {
        log.info(blue("Creating Auction"));

        Auction auction = new Auction()
                .setBeginDate(TimeMachine.now())
                .setExpireDate(TimeMachine.now().plusMinutes(5));
        auction.setBids(bids);
        return auction;
    }

    @Bean
    @Scope("prototype")
    @Primary
    public Auction auctionReallyFast(List<Bid> bids) {
        log.info(green("Creating really fast Auction"));
        Auction auction = new Auction()
                .setBeginDate(TimeMachine.now())
                .setExpireDate(TimeMachine.now().plusSeconds(5));
        auction.setBids(bids);
        return auction;
    }

    @Bean
    @Scope("prototype")
    @Order(value = 1)
    public Bid bidLow() {
        return new Bid().setAmount(BigDecimal.ONE);
    }

    @Bean
    @Scope("prototype")
    @Order(value = 10)
    @Primary
    public Bid bidHigh() {
        return new Bid().setAmount(BigDecimal.TEN);
    }


}
