package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.model.Bid;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.repository.inmemory.AuctionRepositoryInMemoryBean;
import io.crowdcode.speedbay.auction.service.AuctionService;
import io.crowdcode.speedbay.auction.service.AuctionServiceBean;
import io.crowdcode.speedbay.common.AnsiColor;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
public class BusinessLogicConfiguration {

    @Bean
    @Primary
    public AuctionService auctionService(AuctionRepository auctionRepository) {
        AuctionServiceBean auctionService = new AuctionServiceBean();
        auctionService.setAuctionRepository(auctionRepository);
        return auctionService;
    }

    @Bean(name={"auctionRepository"})
    public AuctionRepository auctionRepository(InMemoryStore<Auction> store) {
        AuctionRepositoryInMemoryBean repositoryInMemoryBean = new AuctionRepositoryInMemoryBean();
        repositoryInMemoryBean.setStore(store);
        return repositoryInMemoryBean;
    }

    @Bean
    public List<InMemoryStore> listOfStores(InMemoryStore[] stores) {
        return Arrays.asList(stores);
    }

    @Bean(initMethod = "init")
    @Primary
    public InMemoryStore<Auction> auctionInMemoryStore() {
        log.info(AnsiColor.purple("auctionInMemoryStore with init."));
        return new InMemoryStore();
    }

    @Bean
    public InMemoryStore<Auction> expiredAuctionStore() {
        return new InMemoryStore<>();
    }

    @Bean
    public InMemoryStore<Bid> bidInMemoryStore() {
        return new InMemoryStore();
    }





}
