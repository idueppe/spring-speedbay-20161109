package io.crowdcode.speedbay.auction.repository.inmemory;


import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.service.AuctionService;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Repository
public class AuctionRepositoryInMemoryBean implements AuctionRepository {

    @Autowired
    private InMemoryStore<Auction> store;
    private String name;
    private AuctionService auctionService;

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public Optional<Auction> find(Long auctionId) {
        return Optional.ofNullable(store.load(auctionId));
    }

    @Override
    public Auction findOne(Long auctionId) {
        return store.load(auctionId);
    }

    @Override
    public List<Auction> findAll() {
        return store.loadAll();
    }

    @Override
    public Auction save(Auction auction) {
        store.save(auction);
        return auction;
    }

    public InMemoryStore<Auction> getStore() {
        return store;
    }

    public void setStore(InMemoryStore<Auction> store) {
        this.store = store;
    }
    
    public void setService(AuctionService auctionService) {
        this.auctionService = auctionService;
    }
}
