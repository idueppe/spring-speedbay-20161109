package io.crowdcode.speedbay.auction.util;

import io.crowdcode.speedbay.common.AnsiColor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by idueppe on 22.06.17.
 */
@Slf4j
@Aspect
public class CountBidAdvice {

    private AtomicLong bidCount = new AtomicLong(0l);

    @AfterReturning("execution(* io.crowdcode.speedbay.auction.service.AuctionService.bidOnAuction(..))")
    public void countBid() {
        long count = bidCount.incrementAndGet();
        log.info(AnsiColor.purple("Total bids {}"), count);
    }

    public long getTotalBids() {
        return bidCount.get();
    }

    public void reset() {
        bidCount = new AtomicLong(0l);
    }
}
