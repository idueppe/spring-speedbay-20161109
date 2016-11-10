package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.model.Bid;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.repository.inmemory.AuctionRepositoryInMemoryBean;
import io.crowdcode.speedbay.auction.service.AuctionService;
import io.crowdcode.speedbay.auction.service.AuctionServiceBean;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Configuration
public class BusinessLogicConfiguration {

    @Bean
    public AuctionService auctionService(AuctionRepository auctionRepository) {
        AuctionServiceBean service = new AuctionServiceBean();
        service.setAuctionRepository(auctionRepository);
        return service;
    }

    @Bean
    public AuctionRepositoryInMemoryBean auctionRepositoryInMemoryBean(InMemoryStore<Auction> storeAuction3) {
        AuctionRepositoryInMemoryBean repository = new AuctionRepositoryInMemoryBean();
        repository.setStore((InMemoryStore<Auction>) storeAuction3);
        return repository;
    }

//    @Bean
//    public AuctionRepositoryInMemoryBean auctionRepositoryInMemoryBean2(
//           InMemoryStore<?> storeAuction) {
//        AuctionRepositoryInMemoryBean repository = new AuctionRepositoryInMemoryBean();
//        repository.setStore((InMemoryStore<Auction>) storeAuction);
//        return repository;
//    }


    @Bean()
    public InMemoryStore<Auction> storeAuction() {
        InMemoryStore<Auction> store = new InMemoryStore<>();
        store.init();
        return store;
    }

//    @Bean()
//    public InMemoryStore<Auction> storeAuction3() {
//        InMemoryStore<Auction> store = new InMemoryStore<>();
//        store.init();
//        return store;
//    }


    @Bean(initMethod = "init")
    public InMemoryStore<Bid> storeBid() {
        InMemoryStore<Bid> store = new InMemoryStore<>();
//        store.init();
        return store;
    }

}
