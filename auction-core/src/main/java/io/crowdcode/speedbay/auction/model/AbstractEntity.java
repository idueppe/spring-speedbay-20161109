package io.crowdcode.speedbay.auction.model;

import io.crowdcode.speedbay.common.Identifiable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by idueppe on 05.04.17.
 */
@Getter
@Setter
@Accessors(chain=true)
public  abstract  class AbstractEntity implements Identifiable<Long> {
    private Long id;
}
