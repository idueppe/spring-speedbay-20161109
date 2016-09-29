package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.service.AuctionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AdviceConfiguration.class)
public class AdviceConfigurationTest {

    @Autowired
    private AuctionService auctionService;

    @Test
    public void test() throws Exception {
        auctionService.placeAuction("title", "crowdcode", BigDecimal.ZERO);

    }
}