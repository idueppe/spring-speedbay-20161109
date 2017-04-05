package io.crowdcode.speedbay.auction.model;

import io.crowdcode.speedbay.common.Identifiable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Getter @Setter @Accessors(chain = true)
@EqualsAndHashCode @ToString
public abstract class AbstractEntity implements Identifiable<Long> {

    private Long id;

}
