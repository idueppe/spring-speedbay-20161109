package io.crowdcode.speedbay.auction.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
@Accessors(chain = true) // Message als Return Type der Setter
@ToString
public class Message {

    private String message;
    private String createdBy;
    private LocalDateTime createdAt;

}
