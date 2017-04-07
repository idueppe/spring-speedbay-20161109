package io.crowdcode.speedbay.auction.model;

import io.crowdcode.speedbay.auction.common.LocalDateTimePersistenceConverter;
import io.crowdcode.speedbay.common.time.TimeMachine;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Getter
@Setter
@Accessors(chain=true)
@ToString
//@EqualsAndHashCode
@Entity
public class Auction extends AbstractEntity {

    private String owner;

    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime beginDate;

    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime expireDate;

    private BigDecimal minAmount;

    private String title;
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Bid> bids = new ArrayList<>();

    public Bid getHighestBid() {
        return bids
                .stream()
                .max(Comparator.comparing(Bid::getAmount))
                .orElse(new Bid().setAmount(BigDecimal.ZERO).setEmail("-"));
    }


    public boolean isExpired() {
        return expireDate.isBefore(TimeMachine.now());
    }

    public boolean isRunning() {
        LocalDateTime now = TimeMachine.now();
        return !beginDate.isAfter(now)
                && expireDate.isAfter(now);
    }

}
