package io.crowdcode.speedbay.auction.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Accessors(chain = true) // Message als Return Type der Setter
public class Message {

    private Long id;
    private String name;
    private String body;

    public Message() {
        log.info("new message");
    }

    public static Message createDefault() {
        return new Message()
                .setId(1l)
                .setName("Default");
    }
}
