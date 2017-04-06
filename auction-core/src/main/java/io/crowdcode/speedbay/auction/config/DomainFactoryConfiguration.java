package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.model.Bid;
import io.crowdcode.speedbay.auction.model.DomainFactory;
import io.crowdcode.speedbay.auction.model.DomainFactoryBean;
import io.crowdcode.speedbay.common.AnsiColor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Slf4j
@Configuration
@Import(BusinessLogicConfiguration.class)
public class DomainFactoryConfiguration {

    @Value("#{systemProperties['defaultDurationInMinutes']}")
    private Long defaultDurationInMinutes = 5l;

    @Bean
    public DomainFactory domainFactory() {
        return new DomainFactoryBean();
    }

    @Bean(name="auction")
    @Scope("prototype")
    public Auction createAuction() {
        log.debug(AnsiColor.blue("Creating new Auction Repository with duration of {} minutes."), defaultDurationInMinutes);
        return new Auction()
                .setBeginDate(LocalDateTime.now())
                .setExpireDate(LocalDateTime.now().plusMinutes(defaultDurationInMinutes))
                .setOwner("Default Owner");
    }

    @Bean(name="bid")
    @Scope("prototype")
    public Bid createBid() {
        return new Bid();
    }
}
