package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.common.AnsiColor;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
public class BusinessLogicEnhancedConfiguration {

    @Bean()
    @Primary
    public InMemoryStore<Auction> auctionInMemoryStore() {
        log.info(AnsiColor.purple("auctionInMemoryStore without init."));
        return new InMemoryStore();
    }





}
