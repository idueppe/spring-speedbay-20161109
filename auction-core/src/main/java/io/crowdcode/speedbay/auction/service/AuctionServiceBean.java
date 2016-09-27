package io.crowdcode.speedbay.auction.service;

import lombok.extern.slf4j.Slf4j;

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


    private static final Logger log = LoggerFactory.getLogger(AuctionServiceBean.class);



}
