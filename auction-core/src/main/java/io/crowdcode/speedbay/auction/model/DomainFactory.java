package io.crowdcode.speedbay.auction.model;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public interface DomainFactory {

    Auction createAuction();
    Bid createBid();
}
