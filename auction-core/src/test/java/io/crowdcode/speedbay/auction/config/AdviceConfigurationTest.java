package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.service.AuctionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AdviceConfiguration.class)
public class AdviceConfigurationTest {

    @Autowired
    private AuctionService service;

    @Test
    public void testServiceWithAdvice() throws Exception {
        service.placeAuction("title","description", BigDecimal.TEN);
    }
}