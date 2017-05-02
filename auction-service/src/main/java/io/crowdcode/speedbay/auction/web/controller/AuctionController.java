package io.crowdcode.speedbay.auction.web.controller;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.model.Bid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

/**
 * Created by idueppe on 07.04.17.
 */
@RestController
public class AuctionController {

    @RequestMapping(path = "/auction", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Auction getAuction() {
        return new Auction()
                .setOwner("Auction")
                .setBeginDate(LocalDateTime.now())
                .setExpireDate(LocalDateTime.now().plusMinutes(5))
                .setMinAmount(BigDecimal.ONE)
                .setBids(Collections.singletonList(new Bid().setAmount(BigDecimal.TEN).setEmail("tes†@test.io")));
    }

}
