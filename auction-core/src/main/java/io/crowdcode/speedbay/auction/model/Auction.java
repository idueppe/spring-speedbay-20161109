package io.crowdcode.speedbay.auction.model;

import io.crowdcode.speedbay.common.time.TimeMachine;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class Auction extends AbstractEntity {
    private String owner;
    private LocalDateTime expireDate;
    private LocalDateTime beginDate;
    private BigDecimal minAmount;
    private String title;
    private String description;
    private List<Bid> bids = new ArrayList<>();

    public Bid getHighestBid() {
        return bids
                .stream()
                .max((b1, b2) -> b1.getAmount().compareTo(b2.getAmount()))
                .orElse(new Bid().setAmount(BigDecimal.ZERO).setEmail("-"));
    }

    public boolean isExpired() {
        return expireDate.isBefore(TimeMachine.now());
    }

    public boolean isRunning() {
        LocalDateTime now = TimeMachine.now();
        return (!now.isBefore(beginDate) && expireDate.isAfter(now));
    }

}
