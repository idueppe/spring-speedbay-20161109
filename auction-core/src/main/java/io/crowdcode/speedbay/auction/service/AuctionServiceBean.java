package io.crowdcode.speedbay.auction.service;

import lombok.extern.slf4j.Slf4j;

import static io.crowdcode.speedbay.common.AnsiColor.green;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Slf4j
public class AuctionServiceBean implements AuctionService {

    private String name;

    public AuctionServiceBean() {
        log.info(green("Here I am!"));
    }

    public AuctionServiceBean(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
