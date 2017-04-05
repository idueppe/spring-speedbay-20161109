package io.crowdcode.speedbay.auction.fixture;

import io.crowdcode.speedbay.auction.model.Auction;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class AuctionFixtureTest {

    @Test
    public void buildAuction() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Auction auction = AuctionFixture.buildAuction();
        now.plus(119999, ChronoUnit.MILLIS);
        assertThat(auction.getExpireDate().isAfter(now), is(true));
    }

}