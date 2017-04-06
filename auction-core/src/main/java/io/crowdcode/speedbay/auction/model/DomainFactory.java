package io.crowdcode.speedbay.auction.model;

/**
 * Created by idueppe on 06.04.17.
 */
public interface DomainFactory {

    Auction createAuction();
    Bid createBid();
}
