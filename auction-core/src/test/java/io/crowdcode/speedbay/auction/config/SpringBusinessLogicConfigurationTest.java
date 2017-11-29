package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.model.AuctionFixture;
import io.crowdcode.speedbay.auction.repository.AuctionRepository;
import io.crowdcode.speedbay.auction.service.AuctionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BusinessLogicConfiguration.class)
public class SpringBusinessLogicConfigurationTest {

    @Autowired
    @Qualifier("auctionService")
    private AuctionService auctionService;

    @Autowired
    private AuctionRepository auctionRepository;

    @Test
    @Repeat(10)
    @DirtiesContext
    public void testApplicationContextWithIntegration() throws Exception {
        Auction auction = AuctionFixture.buildDefaultAuction();
        Long auctionId = auctionService.placeAuction(
                auction.getTitle(),
                auction.getDescription(),
                auction.getMinAmount());


        auctionService.bidOnAuction(auctionId, BigDecimal.valueOf(11));
        Auction found = auctionService.findAuction(auctionId);

        assertThat(found.getHighestBid().getAmount().doubleValue(), is(11.0));
        assertThat(auctionService.findRunningAuctions(), hasSize(1));
    }
}