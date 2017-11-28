package io.crowdcode.speedbay.auction.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AuctionTest {

    @Test
    public void testGetHighestBid() throws Exception {
        assertThat(AuctionFixture
                .buildDefaultAuction()
                .getHighestBid()
                .getAmount()
                .doubleValue(), is(10.0));
    }

}