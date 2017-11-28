package io.crowdcode.speedbay.auction.service;

import io.crowdcode.speedbay.auction.exception.AuctionExpiredException;
import io.crowdcode.speedbay.auction.exception.AuctionNotFoundException;
import io.crowdcode.speedbay.auction.exception.BidTooLowException;
import io.crowdcode.speedbay.auction.model.Auction;

import java.math.BigDecimal;
import java.util.List;

public class DefaultAuctionServiceBean implements AuctionService{
    @Override
    public Long placeAuction(String title, String description, BigDecimal minAmount) {
        return null;
    }

    @Override
    public Auction findAuction(Long auctionId) {
        return null;
    }

    @Override
    public List<Auction> findRunningAuctions() {
        return null;
    }

    @Override
    public List<Auction> findExpiredAuctions() {
        return null;
    }

    @Override
    public void bidOnAuction(Long auctionId, BigDecimal amount) throws AuctionNotFoundException, AuctionExpiredException, BidTooLowException {

    }
}
