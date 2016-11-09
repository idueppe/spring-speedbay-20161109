package io.crowdcode.speedbay.auction.service;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Slf4j
@Setter @Getter @Accessors(chain = true)
public class AuctionServiceBean implements AuctionService {

    private String name;

    public AuctionServiceBean() {
        log.debug("AuctionServiceBean Constructor");
    }
}
