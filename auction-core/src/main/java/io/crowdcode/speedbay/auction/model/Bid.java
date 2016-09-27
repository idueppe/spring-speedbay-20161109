package io.crowdcode.speedbay.auction.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Getter @Setter @Accessors(chain = true)
public class Bid extends AbstractEntity {

    private String email;
    private BigDecimal amount;

}
