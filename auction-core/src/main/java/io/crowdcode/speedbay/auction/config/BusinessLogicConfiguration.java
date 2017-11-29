package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.model.Bid;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.repository.AuctionRepositoryInMemoryBean;
import io.crowdcode.speedbay.auction.service.AuctionService;
import io.crowdcode.speedbay.auction.service.AuctionServiceBean;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import java.util.List;

// Java basierte Konfiguration

//@AllianzConfiguration
@Slf4j
@Configuration
public class BusinessLogicConfiguration {

    // Bean Factory Methoden

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
    public String oneInMemoryStore(InMemoryStore<?> store) {
        return "hat geklappt";
    }

    @Bean
    public String allInMemoryStores(List<InMemoryStore> stores) {
        stores.stream().forEach(s -> log.info(s.toString()));
        return "hat auch geklappt";
    }

    @Bean
    @Primary
    @Order(-55)
    public InMemoryStore<Auction> inMemoryStore() {
        return new InMemoryStore<>();
    }

    // das geht seit Spring 4.3
    @Bean(name = {"id", "alias"})
    @Order(-54)
    public InMemoryStore<Bid> inMemoryStoreBid() {
        return new InMemoryStore<>();
    }


}
