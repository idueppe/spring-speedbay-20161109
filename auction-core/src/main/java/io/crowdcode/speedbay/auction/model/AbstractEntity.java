package io.crowdcode.speedbay.auction.model;

import io.crowdcode.speedbay.common.Identifiable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
@ToString
public class AbstractEntity implements Identifiable<Long> {

    private Long id;
}
