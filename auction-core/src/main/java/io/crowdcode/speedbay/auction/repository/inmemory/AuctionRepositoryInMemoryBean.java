package io.crowdcode.speedbay.auction.repository.inmemory;


import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static io.crowdcode.speedbay.common.AnsiColor.green;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Slf4j
@Repository
public class AuctionRepositoryInMemoryBean implements AuctionRepository {

    @Autowired
    private InMemoryStore<Auction> store;

    public AuctionRepositoryInMemoryBean() {
        log.info(green("Constructor of AuctionRepositoryInMemoryBean"));
    }

    @PostConstruct
    private void init() {
        log.info(green("PostConstruct of AuctionRepositoryInMemoryBean"));
        store.init();
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
}
