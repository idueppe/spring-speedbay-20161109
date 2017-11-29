package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.model.Bid;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.repository.AuctionRepositoryInMemoryBean;
import io.crowdcode.speedbay.auction.service.AuctionService;
import io.crowdcode.speedbay.auction.service.AuctionServiceBean;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@AllianzConfiguration
@Configuration
public class BusinessLogicConfiguration {

    @Bean
    public AuctionService auctionService(AuctionRepository auctionRepository) {
        return new AuctionServiceBean(auctionRepository);
    }

    @Bean
    public AuctionRepository auctionRepositoryInMemoryBean(InMemoryStore<Auction> store) {
        AuctionRepositoryInMemoryBean bean = new AuctionRepositoryInMemoryBean();
        bean.setStore(store);
        return bean;
    }

    @Bean
    public InMemoryStore<Auction> inMemoryStore() {
        return new InMemoryStore<>();
    }

    // das geht seit Spring 4.3
    @Bean(name = {"id", "alias"})
    public InMemoryStore<Bid> inMemoryStoreBid() {
        return new InMemoryStore<>();
    }


}
