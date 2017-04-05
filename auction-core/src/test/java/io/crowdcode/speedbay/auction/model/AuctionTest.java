package io.crowdcode.speedbay.auction.model;

import io.crowdcode.speedbay.auction.fixture.AuctionFixture;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;


/**
 * Created by idueppe on 05.04.17.
 */
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