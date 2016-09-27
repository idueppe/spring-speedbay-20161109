package io.crowdcode.speedbay.auction.service;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class AuctionServiceBean implements AuctionService {

    private String name;


    public AuctionServiceBean() {
    }

    public AuctionServiceBean(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
