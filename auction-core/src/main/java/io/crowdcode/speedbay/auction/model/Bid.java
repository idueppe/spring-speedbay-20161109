package io.crowdcode.speedbay.auction.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by idueppe on 04.04.17.
 */
@Getter
@Setter
@Accessors(chain = true)
public class Bid extends AbstractEntity {

    private BigDecimal amount;
    private String email;
}
