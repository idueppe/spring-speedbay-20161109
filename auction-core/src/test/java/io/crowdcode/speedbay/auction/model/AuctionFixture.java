package io.crowdcode.speedbay.auction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class AuctionFixture {

    public static Auction createTestAuction(String title, int amount) {
        return buildAuction()
                .setDescription("description")
                .setMinAmount(BigDecimal.ONE)
                .setTitle(title)
                .setBids(Arrays.asList(
                        new Bid()
                                .setAmount(BigDecimal.valueOf(amount))
                                .setEmail("kontakt@crowdcode.io")));
    }

    public static Auction buildDefaultAuction() {
        return buildAuction()
                .setTitle("MacBook Pro")
                .setMinAmount(BigDecimal.ONE)
                .setDescription("MacBook Pro 15\" Retina")
                .setBids(Arrays.asList(
                        buildLowBid(),
                        buildHighBid())
                );
    }

    public static Auction buildAuction() {
        return new Auction()
                .setBeginDate(LocalDateTime.now())
                .setExpireDate(LocalDateTime.now().plus(2, ChronoUnit.MINUTES))
                .setOwner("unittest");
    }

    public static Bid buildHighBid() {
        return new Bid()
                .setAmount(BigDecimal.TEN)
                .setEmail("test@unit.org");
    }

    public static Bid buildLowBid() {
        return new Bid()
                .setAmount(BigDecimal.ONE)
                .setEmail("unit@test.org");
    }
}