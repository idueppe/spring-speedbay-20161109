package io.crowdcode.speedbay.auction.config;

import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import io.crowdcode.speedbay.auction.model.Auction;
import io.crowdcode.speedbay.auction.service.AuctionService;
import io.crowdcode.speedbay.auction.util.CountBidAdvice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

/**
 * Created by idueppe on 22.06.17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AspectJConfiguration.class)
public class AspectJConfigurationTest {


    @Autowired
    private AuctionService auctionService;

    @Autowired
    private CountBidAdvice countBidAdvice;

    @Test
    public void testApplicationContextWithIntegration() throws Exception {
        countBidAdvice.reset();

        Auction auction = AuctionFixture.buildDefaultAuction();
        Long auctionId = auctionService.placeAuction(
                auction.getTitle(),
                auction.getDescription(),
                auction.getMinAmount());


        auctionService.bidOnAuction(auctionId, BigDecimal.valueOf(11));
        auctionService.bidOnAuction(auctionId, BigDecimal.valueOf(12));
        auctionService.bidOnAuction(auctionId, BigDecimal.valueOf(13));
        auctionService.bidOnAuction(auctionId, BigDecimal.valueOf(14));
        Auction found = auctionService.findAuction(auctionId);

        assertThat(found.getHighestBid().getAmount().doubleValue(), is(14.0));
        assertThat(auctionService.findRunningAuctions(),hasSize(1));


        assertThat(countBidAdvice.getTotalBids(), is(4l));
    }

}