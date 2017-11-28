package io.crowdcode.speedbay.auction.repository;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.common.inmemory.InMemoryStore;

import java.util.List;
import java.util.Optional;

public class AuctionRepositoryInMemoryBean implements AuctionRepository {

    private InMemoryStore<Auction> store;
    private String repositoryName;

    @Override
    public Optional<Auction> find(Long auctionId) {
        return Optional.ofNullable(findOne(auctionId));
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

    public void setStore(InMemoryStore<Auction> store) {
        this.store = store;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }
}
