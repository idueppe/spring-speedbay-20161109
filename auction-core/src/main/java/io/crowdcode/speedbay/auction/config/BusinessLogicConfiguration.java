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
import org.springframework.context.annotation.Primary;

@Configuration
public class BusinessLogicConfiguration {

    @Bean
    public AuctionService auctionService(AuctionRepository auctionRepository) {
        AuctionServiceBean bean = new AuctionServiceBean();
        bean.setAuctionRepository(auctionRepository);
        return bean;
    }

//    public AuctionRepository auctionRepository(@Qualifier("inMemoryStoreForAuctions") InMemoryStore<Auction>
//                                                           inMemoryStore) {
    @Bean
    public AuctionRepository auctionRepository(InMemoryStore<Auction>
                                                           inMemoryStore) {
        AuctionRepositoryInMemoryBean auctionRepository = new AuctionRepositoryInMemoryBean();
        auctionRepository.setStore(inMemoryStore);
        return auctionRepository;
    }

    @Bean
    @Primary
    public InMemoryStore<Auction> inMemoryStoreForAuctions() {
        InMemoryStore<Auction> inMemoryStore = new InMemoryStore<>();
        inMemoryStore.init();
        return inMemoryStore;
    }

    @Bean
    public InMemoryStore<Auction> inMemoryForExpiredAuctions() {
        InMemoryStore<Auction> inMemoryStore = new InMemoryStore<>();
        inMemoryStore.init();
        return inMemoryStore;
    }


    @Bean
    public InMemoryStore<Bid> inMemoryStoreForBids() {
        InMemoryStore<Bid> inMemoryStore = new InMemoryStore<>();
        inMemoryStore.init();
        return inMemoryStore;
    }


}
