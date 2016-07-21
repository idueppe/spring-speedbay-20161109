package io.crowdcode.speedbay.auction.model;

import io.crowdcode.speedbay.common.Identifiable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@MappedSuperclass
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
@ToString
public abstract class AbstractEntity implements Identifiable<Long> {

    @Id
    @GeneratedValue
    private Long id;

}
